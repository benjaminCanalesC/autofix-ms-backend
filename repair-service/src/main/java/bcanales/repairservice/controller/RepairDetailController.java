package bcanales.repairservice.controller;

import bcanales.repairservice.entity.RepairDetailEntity;
import bcanales.repairservice.service.RepairDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/repairDetails")
@CrossOrigin("*")
public class RepairDetailController {
    @Autowired
    private RepairDetailService repairDetailService;
    @GetMapping("/")
    public ResponseEntity<ArrayList<RepairDetailEntity>> getRepairDetails() {
        ArrayList<RepairDetailEntity> repairDetails = repairDetailService.getRepairDetails();
        return ResponseEntity.ok(repairDetails);
    }
}
