package bcanales.surchargeservice.controller;

import bcanales.surchargeservice.dtos.RepairDTO;
import bcanales.surchargeservice.dtos.VehicleDTO;
import bcanales.surchargeservice.service.SurchargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/surcharges")
@CrossOrigin("*")
public class SurchargeController {
    @Autowired
    private SurchargeService surchargeService;

    @GetMapping("/byMileage")
    public ResponseEntity<Double> surchargeByMileage(@RequestBody VehicleDTO vehicle) throws Exception {
        Double discount = surchargeService.surchargeByMileage(vehicle);
        return ResponseEntity.ok(discount);
    }

    @GetMapping("/byVehicleYears")
    public ResponseEntity<Double> surchargeByVehicleYears(@RequestBody VehicleDTO vehicle) throws Exception {
        Double discount = surchargeService.surchargeByVehicleYears(vehicle);
        return ResponseEntity.ok(discount);
    }

    @GetMapping("/byPickupDelay")
    public ResponseEntity<Double> surchargeByPickupDelay(@RequestBody RepairDTO repair) {
        Double discount = surchargeService.surchargeByPickupDelay(repair);
        return ResponseEntity.ok(discount);
    }
}
