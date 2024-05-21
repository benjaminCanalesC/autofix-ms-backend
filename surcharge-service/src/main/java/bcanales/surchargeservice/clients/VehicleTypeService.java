package bcanales.surchargeservice.clients;

import bcanales.surchargeservice.dtos.VehicleTypeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "vehicle-type-service")
public interface VehicleTypeService {
    @GetMapping("/api/vehicleTypes/{id}")
    Optional<VehicleTypeDTO> getVehicleTypeById(@PathVariable("id") Long id);
}
