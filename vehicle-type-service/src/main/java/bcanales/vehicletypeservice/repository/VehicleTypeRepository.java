package bcanales.vehicletypeservice.repository;

import bcanales.vehicletypeservice.entity.VehicleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleTypeEntity, Long> {
}