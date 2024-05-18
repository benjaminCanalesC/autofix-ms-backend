package bcanales.vehicletypeservice.service;

import bcanales.vehicletypeservice.entity.VehicleTypeEntity;
import bcanales.vehicletypeservice.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
}