package bcanales.vehicletypeservice.service;

import bcanales.vehicletypeservice.entity.VehicleTypeEntity;
import bcanales.vehicletypeservice.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VehicleTypeService {
    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    public VehicleTypeEntity saveVehicleType(VehicleTypeEntity vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }

    public ArrayList<VehicleTypeEntity> getVehicleTypes() {
        return (ArrayList<VehicleTypeEntity>) vehicleTypeRepository.findAll();
    }

    public Optional<VehicleTypeEntity> getVehicleTypeById(Long id) {
        return vehicleTypeRepository.findById(id);
    }
}