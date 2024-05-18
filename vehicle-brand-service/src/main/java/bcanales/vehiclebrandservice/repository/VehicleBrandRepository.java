package bcanales.vehiclebrandservice.repository;

import bcanales.vehiclebrandservice.entity.VehicleBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleBrandRepository extends JpaRepository<VehicleBrandEntity, Long> {
}