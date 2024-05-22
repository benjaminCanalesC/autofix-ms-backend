package bcanales.repairservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    private Long id;

    private String plate;
    private String model;
    private int fabricationYear;
    private int mileage;
    private int seats;
    private Long vehicleBrandId;
    private Long vehicleEngineId;
    private Long vehicleTypeId;
}