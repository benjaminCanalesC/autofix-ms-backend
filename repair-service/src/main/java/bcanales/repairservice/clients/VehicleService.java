package bcanales.repairservice.clients;

import bcanales.repairservice.dtos.VehicleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vehicle-service")
public interface VehicleService {
    @GetMapping("/api/vehicles/{id}")
    VehicleDTO getVehicleById(@PathVariable("id") Long id);
}
