package bcanales.repairservice.repository;

import bcanales.repairservice.entity.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long> {
    int countByVehicleId(Long vehicleId);

    List<RepairEntity> getRepairsByVehicleId(Long vehicleId);
}