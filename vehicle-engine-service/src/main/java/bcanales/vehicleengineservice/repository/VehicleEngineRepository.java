package bcanales.vehicleengineservice.repository;

import bcanales.vehicleengineservice.entity.VehicleEngineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleEngineRepository extends JpaRepository<VehicleEngineEntity, Long> {
}

