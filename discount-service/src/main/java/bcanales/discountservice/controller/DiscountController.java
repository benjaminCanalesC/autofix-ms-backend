package bcanales.discountservice.controller;

import bcanales.discountservice.dtos.RepairDTO;
import bcanales.discountservice.dtos.VehicleDTO;
import bcanales.discountservice.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discounts")
@CrossOrigin("*")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    @PostMapping("/byRepairs")
    public ResponseEntity<Double> discountByRepairs(@RequestBody VehicleDTO vehicle) {
        Double discount = discountService.discountByRepairs(vehicle);
        return ResponseEntity.ok(discount);
    }

    @PostMapping("/byAttentionDays")
    public ResponseEntity<Double> discountByAttentionDays(@RequestBody RepairDTO repair) {
        Double discount = discountService.discountByAttentionDays(repair);
        return ResponseEntity.ok(discount);
    }
}
