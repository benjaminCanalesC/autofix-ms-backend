package bcanales.repairservice.service;

import bcanales.repairservice.clients.RepairTypeService;
import bcanales.repairservice.clients.VehicleEngineService;
import bcanales.repairservice.clients.VehicleService;
import bcanales.repairservice.dtos.RepairTypeDTO;
import bcanales.repairservice.dtos.VehicleDTO;
import bcanales.repairservice.entity.RepairDetailEntity;
import bcanales.repairservice.repository.RepairDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if (vehicleEngine.equals("Gasolina")) {
            Long repairTypeId = repairDetail.getRepairTypeId();
            RepairTypeDTO repairType = repairTypeService.getRepairTypeById(repairTypeId).get();
            baseRepairCost = repairType.getGasolineCost();
        } else if (vehicleEngine.equals("Diesel")) {
            Long repairTypeId = repairDetail.getRepairTypeId();
            RepairTypeDTO repairType = repairTypeService.getRepairTypeById(repairTypeId).get();
            baseRepairCost = repairType.getDieselCost();
        } else if (vehicleEngine.equals("Hibrido")) {
            Long repairTypeId = repairDetail.getRepairTypeId();
            RepairTypeDTO repairType = repairTypeService.getRepairTypeById(repairTypeId).get();
            baseRepairCost = repairType.getHybridCost();
        } else if (vehicleEngine.equals("Electrico")) {
            Long repairTypeId = repairDetail.getRepairTypeId();
            RepairTypeDTO repairType = repairTypeService.getRepairTypeById(repairTypeId).get();
            baseRepairCost = repairType.getElectricCost();
        } else {
            throw new Exception("Invalid Engine");
        }

        repairDetail.setRepairCost(baseRepairCost);

        return repairDetailRepository.save(repairDetail);
    }
}