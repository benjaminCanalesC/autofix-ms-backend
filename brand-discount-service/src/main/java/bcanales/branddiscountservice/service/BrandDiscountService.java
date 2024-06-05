package bcanales.branddiscountservice.service;

import bcanales.branddiscountservice.clients.VehicleBrandService;
import bcanales.branddiscountservice.clients.VehicleService;
import bcanales.branddiscountservice.dtos.RepairDTO;
import bcanales.branddiscountservice.dtos.VehicleBrandDTO;
import bcanales.branddiscountservice.dtos.VehicleDTO;
import bcanales.branddiscountservice.entity.BrandDiscountEntity;
import bcanales.branddiscountservice.repository.BrandDiscountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BrandDiscountService {
    @Autowired
    private BrandDiscountRepository brandDiscountRepository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleBrandService vehicleBrandService;

    public BrandDiscountEntity saveBrandDiscount(BrandDiscountEntity brandDiscount) {
        return brandDiscountRepository.save(brandDiscount);
    }

    public ArrayList<BrandDiscountEntity> getBrandDiscounts() {
        return (ArrayList<BrandDiscountEntity>) brandDiscountRepository.findAll();
    }

    public boolean deleteBrandDiscount(Long id) throws Exception {
        try {
            brandDiscountRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public int calculateBrandDiscount(RepairDTO repair) throws Exception {
        if (repair.isBonusDiscount()) {
            VehicleDTO vehicle = vehicleService.getVehicleById(repair.getVehicleId()).get();

            Long brandId = vehicle.getVehicleBrandId();

            VehicleBrandDTO vehicleBrand = vehicleBrandService.getVehicleBrandById(brandId)
                    .orElseThrow(() -> new EntityNotFoundException("Vehicle brand with id " + brandId + "does not exist."));

            BrandDiscountEntity brandDiscount = brandDiscountRepository.findBrandDiscountEntityByVehicleBrandId(vehicleBrand.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Brand discount for " + vehicleBrand.getBrand() + "does not exist."));

            if (brandDiscount.getQuantity() != 0) {
                brandDiscount.setQuantity(brandDiscount.getQuantity() - 1);

                int brandDiscountAmount = brandDiscount.getAmount();

                if (brandDiscount.getQuantity() == 0) {
                    deleteBrandDiscount(brandDiscount.getId());
                }

                return brandDiscountAmount;
            }
        }
        return 0;
    }
}
