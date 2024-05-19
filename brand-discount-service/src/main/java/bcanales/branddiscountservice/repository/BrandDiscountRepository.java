package bcanales.branddiscountservice.repository;

import bcanales.branddiscountservice.dtos.VehicleBrandDTO;
import bcanales.branddiscountservice.entity.BrandDiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandDiscountRepository extends JpaRepository<BrandDiscountEntity, Long> {
    Optional<BrandDiscountEntity> findBrandDiscountEntityByVehicleBrandId(Long VehicleBrandId);
}
