package bcanales.repairtypeservice.service;

import bcanales.repairtypeservice.entity.RepairTypeEntity;
import bcanales.repairtypeservice.repository.RepairTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RepairTypeService {
    @Autowired
    RepairTypeRepository repairTypeRepository;

    public RepairTypeEntity saveRepairType(RepairTypeEntity repairType) {
        return repairTypeRepository.save(repairType);
    }

    public ArrayList<RepairTypeEntity> getRepairTypes() {
        return (ArrayList<RepairTypeEntity>) repairTypeRepository.findAll();
    }

    public Optional<RepairTypeEntity> getRepairTypeById(Long id) {
        return repairTypeRepository.findById(id);
    }

    public boolean deleteRepairType(Long id) throws Exception {
        try{
            repairTypeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
