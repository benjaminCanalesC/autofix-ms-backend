package bcanales.branddiscountservice.clients;

import bcanales.branddiscountservice.dtos.VehicleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "vehicle-service")
public interface VehicleService {
    @GetMapping("/api/vehicles/{id}")
    Optional<VehicleDTO> getVehicleById(@PathVariable("id") Long id);
}
