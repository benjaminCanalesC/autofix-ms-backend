package bcanales.repairservice.service;

import bcanales.repairservice.clients.*;
import bcanales.repairservice.dtos.RepairTypeDTO;
import bcanales.repairservice.dtos.VehicleDTO;
import bcanales.repairservice.entity.RepairEntity;
import bcanales.repairservice.repository.RepairRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RepairService {
    @Autowired
    RepairRepository repairRepository;
    @Autowired
    VehicleEngineService vehicleEngineService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    RepairTypeService repairTypeService;
    @Autowired
    DiscountService discountService;
    @Autowired
    SurchargeService surchargeService;
    @Autowired
    BrandDiscountService brandDiscountService;

    public RepairEntity saveRepair(RepairEntity repair) throws Exception {
        int repairCost = calculateRepairCost(repair);
        repair.setRepairCost(repairCost);
        return repairRepository.save(repair);
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

        if (repair.getExitDateTime() != existingRepair.getExitDateTime()) {
            existingRepair.setExitDateTime(repair.getExitDateTime());
        }

        if (repair.getPickupDateTime() != existingRepair.getPickupDateTime()) {
            existingRepair.setPickupDateTime(repair.getPickupDateTime());

            double surchargeByPickupDelayPercentage = surchargeService.surchargeByPickupDelay(existingRepair);

            int surchargeByPickupDelay = (int) (surchargeByPickupDelayPercentage * existingRepair.getBaseRepairCost());

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

    public int calculateRepairCost(RepairEntity repair) throws Exception {
        VehicleDTO vehicle = vehicleService.getVehicleById(repair.getVehicleId());

        Long vehicleEngineId = vehicle.getVehicleEngineId();

        String vehicleEngine = vehicleEngineService.getVehicleEngineById(vehicleEngineId).get().getEngine();

        int baseRepairCost;

        if (vehicleEngine.equals("Gasolina")) {
            Long repairTypeId = repair.getRepairTypeId();
            RepairTypeDTO repairType = repairTypeService.getRepairTypeById(repairTypeId).get();
            baseRepairCost = repairType.getGasolineCost();
        } else if (vehicleEngine.equals("Diesel")) {
            Long repairTypeId = repair.getRepairTypeId();
            RepairTypeDTO repairType = repairTypeService.getRepairTypeById(repairTypeId).get();
            baseRepairCost = repairType.getDieselCost();
        } else if (vehicleEngine.equals("Hibrido")) {
            Long repairTypeId = repair.getRepairTypeId();
            RepairTypeDTO repairType = repairTypeService.getRepairTypeById(repairTypeId).get();
            baseRepairCost = repairType.getHybridCost();
        } else if (vehicleEngine.equals("Electrico")) {
            Long repairTypeId = repair.getRepairTypeId();
            RepairTypeDTO repairType = repairTypeService.getRepairTypeById(repairTypeId).get();
            baseRepairCost = repairType.getElectricCost();
        } else {
            throw new Exception("Invalid Engine");
        }

        repair.setBaseRepairCost(baseRepairCost);

        double discountPercentage = discountService.discountByRepairs(vehicle) +
                discountService.discountByAttentionDays(repair);
        int bonusDiscount = brandDiscountService.calculateBrandDiscount(repair);
        int discount = (int) Math.round(discountPercentage * baseRepairCost) + bonusDiscount;

        repair.setDiscount(discount);

        double surchargePercentage = surchargeService.surchargeByMileage(vehicle) +
                surchargeService.surchargeByVehicleYears(vehicle);
        int surcharge = (int) Math.round(surchargePercentage * baseRepairCost);

        repair.setSurcharge(surcharge);

        return baseRepairCost - discount + surcharge;
    }
}