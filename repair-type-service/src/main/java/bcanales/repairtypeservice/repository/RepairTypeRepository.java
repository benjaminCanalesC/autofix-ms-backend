package bcanales.repairtypeservice.repository;

import bcanales.repairtypeservice.entity.RepairTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairTypeRepository extends JpaRepository<RepairTypeEntity, Long> {
}