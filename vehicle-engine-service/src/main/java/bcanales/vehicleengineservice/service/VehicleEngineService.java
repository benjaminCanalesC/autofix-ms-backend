package bcanales.vehicleengineservice.service;

import bcanales.vehicleengineservice.entity.VehicleEngineEntity;
import bcanales.vehicleengineservice.repository.VehicleEngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VehicleEngineService {
    @Autowired
    VehicleEngineRepository vehicleEngineRepository;

    public VehicleEngineEntity saveVehicleEngine(VehicleEngineEntity vehicleEngine) {
        return vehicleEngineRepository.save(vehicleEngine);
    }

    public ArrayList<VehicleEngineEntity> getVehicleEngines() {
        return (ArrayList<VehicleEngineEntity>) vehicleEngineRepository.findAll();
    }

    public Optional<VehicleEngineEntity> getVehicleTypeById(Long id) {
        return vehicleEngineRepository.findById(id);
    }

}
