package bcanales.branddiscountservice.clients;

import bcanales.branddiscountservice.dtos.VehicleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "vehicle-service", url = "http://vehicle-service")
public interface VehicleService {
    @GetMapping("/vehicles/{id}")
    Optional<VehicleDTO> getVehicleById(@PathVariable Long id);
}
