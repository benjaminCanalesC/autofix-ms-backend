package bcanales.repairservice.clients;

import bcanales.repairservice.dtos.VehicleDTO;
import bcanales.repairservice.entity.RepairEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "surcharge-service")
public interface SurchargeService {
    @PostMapping("/api/surcharges/byMileage")
    Double surchargeByMileage(@RequestBody VehicleDTO vehicle);

    @PostMapping("/api/surcharges/byVehicleYears")
    Double surchargeByVehicleYears(@RequestBody VehicleDTO vehicle);

    @PostMapping("/api/surcharges/byPickupDelay")
    Double surchargeByPickupDelay(@RequestBody RepairEntity repair);
}
