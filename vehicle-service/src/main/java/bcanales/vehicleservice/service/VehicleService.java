package bcanales.vehicleservice.service;

import bcanales.vehicleservice.entity.VehicleEntity;
import bcanales.vehicleservice.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;
    public VehicleEntity saveVehicle(VehicleEntity vehicle) throws Exception {
        //Correctly plate analyze
        String plate = vehicle.getPlate();
        if (plate.length() != 6) {
            throw new Exception("Plate length does not match");
        }

        for (int i = 0; i < plate.length(); i++) {
            char c = plate.charAt(i);
            if (i < 4) {
                if (!Character.isLetter(c)) {
                    throw new Exception("Invalid plate format");
                }
            } else {
                if (!Character.isDigit(c)) {
                    throw new Exception("Invalid plate format");
                }
            }
        }

        return vehicleRepository.save(vehicle);
    }

    public ArrayList<VehicleEntity> getVehicles() {
        return (ArrayList<VehicleEntity>) vehicleRepository.findAll();
    }

    public Optional<VehicleEntity> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Optional<VehicleEntity> getVehicleByPlate(String plate) { return vehicleRepository.findByPlate(plate); }

    public VehicleEntity updateVehicle(VehicleEntity vehicle) {
        System.out.println(vehicle.getId());
        VehicleEntity existingVehicle = vehicleRepository.findById(vehicle.getId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle with id " + vehicle.getId() + "does not exist."));

        if (vehicle.getVehicleBrandId() != null) {
            existingVehicle.setVehicleBrandId(vehicle.getVehicleBrandId());
        }

        if (vehicle.getVehicleEngineId() != null) {
            existingVehicle.setVehicleEngineId(vehicle.getVehicleEngineId());
        }

        if (vehicle.getVehicleTypeId() != null) {
            existingVehicle.setVehicleTypeId(vehicle.getVehicleTypeId());
        }

        if (vehicle.getModel() != null) {
            existingVehicle.setModel(vehicle.getModel());
        }

        if (vehicle.getMileage() != 0) {
            existingVehicle.setMileage(vehicle.getMileage());
        }

        if (vehicle.getSeats() != 0) {
            existingVehicle.setSeats(vehicle.getSeats());
        }

        if (vehicle.getFabricationYear() != 0) {
            existingVehicle.setFabricationYear(vehicle.getFabricationYear());
        }

        return vehicleRepository.save(existingVehicle);
    }

    public boolean deleteVehicle(Long id) throws Exception {
        try{
            vehicleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}