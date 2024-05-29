package bcanales.summary1_service.service;

import bcanales.summary1_service.clients.RepairDetailService;
import bcanales.summary1_service.clients.RepairTypeService;
import bcanales.summary1_service.clients.VehicleService;
import bcanales.summary1_service.clients.VehicleTypeService;
import bcanales.summary1_service.dtos.RepairDetailDTO;
import bcanales.summary1_service.dtos.RepairTypeDTO;
import bcanales.summary1_service.dtos.SummaryDTO;
import bcanales.summary1_service.dtos.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SummaryService {
    @Autowired
    private RepairDetailService repairDetailService;
    @Autowired
    private RepairTypeService repairTypeService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleTypeService vehicleTypeService;

    public ArrayList<SummaryDTO> getSummary(int month, int year) {
        List<RepairTypeDTO> repairTypes = repairTypeService.getRepairTypes();

        ArrayList<RepairDetailDTO> repairDetails = repairDetailService.getRepairDetails();

        ArrayList<SummaryDTO> summaries = new ArrayList<>();

        for (RepairTypeDTO repairType : repairTypes) {
            String repairName = repairType.getRepairType();

            int hatchbackCount = 0;
            int hatchbackAmount = 0;

            int suvCount = 0;
            int suvAmount = 0;

            int sedanCount = 0;
            int sedanAmount = 0;

            int pickupCount = 0;
            int pickupAmount = 0;

            int vanCount = 0;
            int vanAmount = 0;

            int totalCount = 0;
            int totalAmount = 0;

            for (RepairDetailDTO repairDetail : repairDetails) {
                if (Objects.equals(repairType.getId(), repairDetail.getRepairTypeId()) &&
                        repairDetail.getRepairDateTime().getMonth().getValue() == month &&
                        repairDetail.getRepairDateTime().getYear() == year) {
                    String vehiclePlate = repairDetail.getVehiclePlate();
                    VehicleDTO vehicleDTO = new VehicleDTO();
                    vehicleDTO.setPlate(vehiclePlate);
                    VehicleDTO vehicle = vehicleService.getVehicleByPlate(vehicleDTO);

                    Long vehicleTypeId = vehicle.getVehicleTypeId();

                    String vehicleType = vehicleTypeService.getVehicleTypeById(vehicleTypeId).get().getType();

                    if (vehicleType.equals("Sedan")) {
                        sedanCount += 1;
                        sedanAmount += repairDetail.getRepairCost();
                    } else if (vehicleType.equals("Hatchback")) {
                        hatchbackCount += 1;
                        hatchbackAmount += repairDetail.getRepairCost();
                    } else if (vehicleType.equals("SUV")) {
                        suvCount += 1;
                        suvAmount += repairDetail.getRepairCost();
                    } else if (vehicleType.equals("Pickup")) {
                        pickupCount += 1;
                        pickupAmount += repairDetail.getRepairCost();
                    } else {
                        vanCount += 1;
                        vanAmount += repairDetail.getRepairCost();
                    }

                    totalCount += 1;
                    totalAmount += repairDetail.getRepairCost();
                }
            }

            summaries.add(new SummaryDTO(repairName, hatchbackCount, hatchbackAmount, suvCount, suvAmount,
                    sedanCount, sedanAmount, pickupCount, pickupAmount, vanCount, vanAmount, totalCount, totalAmount));
        }

        return summaries;
    }
}
