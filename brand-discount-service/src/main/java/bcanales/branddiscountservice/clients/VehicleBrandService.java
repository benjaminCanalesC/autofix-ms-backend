package bcanales.branddiscountservice.clients;

import bcanales.branddiscountservice.dtos.VehicleBrandDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "vehicle-brand-service")
public interface VehicleBrandService {
    @GetMapping("/api/vehicleBrands/{id}")
    Optional<VehicleBrandDTO> getVehicleBrandById(@PathVariable("id") Long id);
}