package bcanales.branddiscountservice.controller;

import bcanales.branddiscountservice.dtos.RepairDTO;
import bcanales.branddiscountservice.entity.BrandDiscountEntity;
import bcanales.branddiscountservice.service.BrandDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brandDiscounts")
public class BrandDiscountController {
    @Autowired
    BrandDiscountService brandDiscountService;

    @PostMapping("/")
    public ResponseEntity<BrandDiscountEntity> saveBrandDiscount(@RequestBody BrandDiscountEntity brandDiscount) {
        BrandDiscountEntity newBrandDiscount = brandDiscountService.saveBrandDiscount(brandDiscount);
        return ResponseEntity.ok(newBrandDiscount);
    }

    @PostMapping("/calculate")
    public int calculateBrandDiscount(@RequestBody RepairDTO repair) throws Exception {
        return brandDiscountService.calculateBrandDiscount(repair);
    }

    @GetMapping("/")
    public ResponseEntity<List<BrandDiscountEntity>> getBrandDiscounts() {
        List<BrandDiscountEntity> brandDiscounts = brandDiscountService.getBrandDiscounts();
        return ResponseEntity.ok(brandDiscounts);
    }
}
