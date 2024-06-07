package bcanales.vehiclebrandservice.controller;

import bcanales.vehiclebrandservice.entity.VehicleBrandEntity;
import bcanales.vehiclebrandservice.service.VehicleBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicleBrands")
public class VehicleBrandController {
    @Autowired
    VehicleBrandService vehicleBrandService;

    @PostMapping("/")
    public ResponseEntity<VehicleBrandEntity> saveVehicleBrand(@RequestBody VehicleBrandEntity vehicleBrand) {
        VehicleBrandEntity newVehicleBrand = vehicleBrandService.saveVehicleBrand(vehicleBrand);
        return ResponseEntity.ok(newVehicleBrand);
    }

    @GetMapping("/")
    public ResponseEntity<List<VehicleBrandEntity>> getVehicleBrands() {
        List<VehicleBrandEntity> vehicleBrands = vehicleBrandService.getVehicleBrands();
        return ResponseEntity.ok(vehicleBrands);
    }

    @GetMapping("/{id}")
    public Optional<VehicleBrandEntity> getVehicleBrandById(@PathVariable Long id) {
        return vehicleBrandService.getVehicleBrandById(id);
    }
}
