package bcanales.repairservice.clients;

import bcanales.repairservice.dtos.VehicleEngineDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "vehicle-engine-service")
public interface VehicleEngineService {
    @GetMapping("/api/vehicleEngines/{id}")
    public Optional<VehicleEngineDTO> getVehicleEngineById(@PathVariable Long id);
}
