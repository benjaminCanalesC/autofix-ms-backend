package bcanales.repairservice.service;

import bcanales.repairservice.clients.RepairTypeService;
import bcanales.repairservice.clients.VehicleEngineService;
import bcanales.repairservice.clients.VehicleService;
import bcanales.repairservice.dtos.RepairTypeDTO;
import bcanales.repairservice.dtos.VehicleDTO;
import bcanales.repairservice.entity.RepairDetailEntity;
import bcanales.repairservice.entity.RepairEntity;
import bcanales.repairservice.repository.RepairDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RepairDetailService {
    @Autowired
    private RepairDetailRepository repairDetailRepository;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private RepairTypeService repairTypeService;
    @Autowired
    private VehicleEngineService vehicleEngineService;

    public RepairDetailEntity saveRepairDetail(RepairDetailEntity repairDetail) throws Exception {
        String vehiclePlate = repairDetail.getVehiclePlate();
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setPlate(vehiclePlate);
        VehicleDTO vehicle = vehicleService.getVehicleByPlate(vehicleDTO);

        System.out.println(repairDetail.getRepairTypeId());

        Long vehicleEngineId = vehicle.getVehicleEngineId();

        String vehicleEngine = vehicleEngineService.getVehicleEngineById(vehicleEngineId).get().getEngine();

        int baseRepairCost;

        Long repairTypeId = repairDetail.getRepairTypeId();
        RepairTypeDTO repairType = repairTypeService.getRepairTypeById(repairTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Repair Type with id " + repairTypeId + " does not exist."));

        if (vehicleEngine.equals("Gasolina")) {
            baseRepairCost = repairType.getGasolineCost();
        } else if (vehicleEngine.equals("Diesel")) {
            baseRepairCost = repairType.getDieselCost();
        } else if (vehicleEngine.equals("Hibrido")) {
            baseRepairCost = repairType.getHybridCost();
        } else if (vehicleEngine.equals("Electrico")) {
            baseRepairCost = repairType.getElectricCost();
        } else {
            throw new Exception("Invalid Engine");
        }

        repairDetail.setRepairCost(baseRepairCost);

        return repairDetailRepository.save(repairDetail);
    }

    public ArrayList<RepairDetailEntity> getRepairDetails() {
        return (ArrayList<RepairDetailEntity>) repairDetailRepository.findAll();
    }
}