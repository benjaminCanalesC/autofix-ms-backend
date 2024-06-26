package bcanales.vehicleengineservice.controller;

import bcanales.vehicleengineservice.entity.VehicleEngineEntity;
import bcanales.vehicleengineservice.service.VehicleEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicleEngines")
public class VehicleEngineController {
    @Autowired
    VehicleEngineService vehicleEngineService;

    @PostMapping("/")
    public ResponseEntity<VehicleEngineEntity> saveVehicleEngine(@RequestBody VehicleEngineEntity vehicleEngine) {
        VehicleEngineEntity newVehicleEngine = vehicleEngineService.saveVehicleEngine(vehicleEngine);
        return ResponseEntity.ok(newVehicleEngine);
    }

    @GetMapping("/")
    public ResponseEntity<List<VehicleEngineEntity>> getVehicleEngines() {
        List<VehicleEngineEntity> vehicleEngines = vehicleEngineService.getVehicleEngines();
        return ResponseEntity.ok(vehicleEngines);
    }

    @GetMapping("/{id}")
    public Optional<VehicleEngineEntity> getVehicleEngineById(@PathVariable Long id) {
        return vehicleEngineService.getVehicleTypeById(id);
    }
}
