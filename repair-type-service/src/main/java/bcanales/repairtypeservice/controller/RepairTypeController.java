package bcanales.repairtypeservice.controller;

import bcanales.repairtypeservice.entity.RepairTypeEntity;
import bcanales.repairtypeservice.service.RepairTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/repairTypes")
@CrossOrigin("*")
public class RepairTypeController {
    @Autowired
    RepairTypeService repairTypeService;

    @PostMapping("/")
    public ResponseEntity<RepairTypeEntity> saveRepairType(@RequestBody RepairTypeEntity repairType) {
        RepairTypeEntity newRepairType = repairTypeService.saveRepairType(repairType);
        return ResponseEntity.ok(newRepairType);
    }

    @GetMapping("/")
    public ResponseEntity<List<RepairTypeEntity>> getRepairTypes() {
        List<RepairTypeEntity> repairTypes = repairTypeService.getRepairTypes();
        return ResponseEntity.ok(repairTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<RepairTypeEntity>> getRepairTypeById(@PathVariable Long id) {
        Optional<RepairTypeEntity> repairType = repairTypeService.getRepairTypeById(id);
        return ResponseEntity.ok(repairType);
    }
}