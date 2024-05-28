package bcanales.summary1_service.clients;

import bcanales.summary1_service.dtos.VehicleTypeDTO;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Optional;

@FeignClient(name = "vehicle-type-service")
public interface VehicleTypeService {
    @GetMapping("/api/vehicleTypes/{id}")
    Optional<VehicleTypeDTO> getVehicleTypeById(@PathVariable("id") Long id);
}