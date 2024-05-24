package bcanales.repairservice.repository;

import bcanales.repairservice.entity.RepairDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairDetailRepository extends JpaRepository<RepairDetailEntity, Long> {
}