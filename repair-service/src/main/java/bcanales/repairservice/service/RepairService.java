package bcanales.repairservice.service;

import bcanales.repairservice.clients.*;
import bcanales.repairservice.dtos.VehicleDTO;
import bcanales.repairservice.entity.RepairDetailEntity;
import bcanales.repairservice.entity.RepairEntity;
import bcanales.repairservice.repository.RepairRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RepairService {
    @Autowired
    RepairRepository repairRepository;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    DiscountService discountService;
    @Autowired
    SurchargeService surchargeService;
    @Autowired
    BrandDiscountService brandDiscountService;
    @Autowired
    RepairDetailService repairDetailService;

    @Transactional
    public RepairEntity saveRepair(RepairEntity repair) throws Exception {
        VehicleDTO vehicle = vehicleService.getVehicleById(repair.getVehicleId());

        repair.setVehicleId(vehicle.getId());

        RepairEntity savedRepair = repairRepository.save(repair);

        int baseRepairCost = 0;

        for (RepairDetailEntity detail : repair.getRepairDetails()) {
            detail.setRepair(savedRepair);
            detail.setVehiclePlate(vehicle.getPlate());

            RepairDetailEntity repairDetail = repairDetailService.saveRepairDetail(detail);

            baseRepairCost += repairDetail.getRepairCost();
        }

        savedRepair.setBaseRepairCost(baseRepairCost);

        double discountPercentage = discountService.discountByRepairs(vehicle) +
                discountService.discountByAttentionDays(savedRepair);
        int bonusDiscount = brandDiscountService.calculateBrandDiscount(savedRepair);
        int discount = (int) Math.round(discountPercentage * baseRepairCost) + bonusDiscount;

        savedRepair.setDiscount(discount);

        double surchargePercentage = surchargeService.surchargeByMileage(vehicle) +
                surchargeService.surchargeByVehicleYears(vehicle);
        int surcharge = (int) Math.round(surchargePercentage * baseRepairCost);

        savedRepair.setSurcharge(surcharge);

        int subtotalCost = baseRepairCost - discount + surcharge;
        savedRepair.setSubtotalCost(subtotalCost);
        savedRepair.setRepairCost(subtotalCost);

        return repairRepository.save(savedRepair);
    }

    public int getRepairsAmountByVehicleId(Long vehicleId) { return repairRepository.countByVehicleId(vehicleId); }

    public Optional<RepairEntity> getRepairById(Long id) {
        return repairRepository.findById(id);
    }

    public ArrayList<RepairEntity> getRepairs() {
        return (ArrayList<RepairEntity>) repairRepository.findAll();
    }

    public boolean deleteRepair(Long id) throws Exception {
        try {
            repairRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public RepairEntity updateRepair(RepairEntity repair) {
        RepairEntity existingRepair = repairRepository.findById(repair.getId())
                .orElseThrow(() -> new EntityNotFoundException("Repair with id " + repair.getId() + " does not exist."));

        if (repair.getExitDateTime() != null) {
            existingRepair.setExitDateTime(repair.getExitDateTime());
        }

        if (repair.getPickupDateTime() != null) {
            existingRepair.setPickupDateTime(repair.getPickupDateTime());

            double surchargeByPickupDelayPercentage = surchargeService.surchargeByPickupDelay(existingRepair);

            int surchargeByPickupDelay = (int) (surchargeByPickupDelayPercentage * existingRepair.getBaseRepairCost());

            int subtotal = existingRepair.getSubtotalCost() + surchargeByPickupDelay;
            existingRepair.setSubtotalCost(subtotal);

            // Se actualizan el recargo de la reparación
            int totalSurcharge = existingRepair.getSurcharge() + surchargeByPickupDelay;
            existingRepair.setSurcharge(totalSurcharge);

            // Se actualiza el costo total
            int totalCost = existingRepair.getRepairCost() + surchargeByPickupDelay;

            // Aplicación del IVa
            int iva = (int) (totalCost * 0.19);
            existingRepair.setIva(iva);

            existingRepair.setRepairCost(totalCost + iva);
        }

        return repairRepository.save(existingRepair);
    }
}