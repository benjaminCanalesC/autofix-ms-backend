package bcanales.discountservice.service;

import bcanales.discountservice.clients.RepairService;
import bcanales.discountservice.dtos.RepairDTO;
import bcanales.discountservice.dtos.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Service
public class DiscountService {
    @Autowired
    private RepairService repairService;
    public double discountByRepairs(VehicleDTO vehicle) {
        int vehicleRepairs = repairService.getRepairsAmountByVehicleId(vehicle.getId());
        String vehicleEngine = String.valueOf(vehicle.getVehicleEngineId());

        if (vehicleEngine.equals("Gasolina")){
            if (vehicleRepairs == 0) { return 0; }
            else if (vehicleRepairs <= 2) { return 0.05; }
            else if (vehicleRepairs <= 5) { return 0.1; }
            else if (vehicleRepairs <= 9) { return 0.15; }
            else { return 0.2; }
        } else if (vehicleEngine.equals("Diesel")) {
            if (vehicleRepairs == 0) { return 0; }
            else if (vehicleRepairs <= 2) { return 0.07; }
            else if (vehicleRepairs <= 5) { return 0.12; }
            else if (vehicleRepairs <= 9) { return 0.17; }
            else { return 0.22; }
        } else if (vehicleEngine.equals("Hibrido")) {
            if (vehicleRepairs == 0) { return 0; }
            else if (vehicleRepairs <= 2) { return 0.1; }
            else if (vehicleRepairs <= 5) { return 0.15; }
            else if (vehicleRepairs <= 9) { return 0.2; }
            else { return 0.25; }
        } else {
            if (vehicleRepairs == 0) { return 0; }
            else if (vehicleRepairs <= 2) { return 0.08; }
            else if (vehicleRepairs <= 5) { return 0.13; }
            else if (vehicleRepairs <= 9) { return 0.18; }
            else { return 0.23; }
        }
    }

    public double discountByAttentionDays(RepairDTO repair) {
        DayOfWeek entryDate = repair.getEntryDateTime().getDayOfWeek();
        int entryTime = repair.getEntryDateTime().getHour();

        if (entryDate.equals(DayOfWeek.MONDAY)  || entryDate.equals(DayOfWeek.THURSDAY)) {
            if (entryTime >= 9 && entryTime <= 12) {
                return 0.1;
            }
        }
        return 0;
    }
}