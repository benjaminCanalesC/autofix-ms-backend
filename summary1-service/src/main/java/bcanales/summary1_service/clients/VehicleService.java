package bcanales.summary1_service.clients;

import bcanales.summary1_service.dtos.VehicleDTO;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "vehicle-service")
public interface VehicleService {
    @GetMapping("/api/vehicles/{id}")
    VehicleDTO getVehicleById(@PathVariable("id") Long id);

    @PostMapping("/api/vehicles/plate")
    VehicleDTO getVehicleByPlate(@RequestBody VehicleDTO vehicleDTO);
}