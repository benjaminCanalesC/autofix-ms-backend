package bcanales.branddiscountservice.controller;

import bcanales.branddiscountservice.entity.BrandDiscountEntity;
import bcanales.branddiscountservice.service.BrandDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brandDiscounts")
@CrossOrigin("*")
public class BrandDiscountController {
    @Autowired
    BrandDiscountService brandDiscountService;

    @PostMapping("/")
    public ResponseEntity<BrandDiscountEntity> saveBrandDiscount(@RequestBody BrandDiscountEntity brandDiscount) {
        BrandDiscountEntity newBrandDiscount = brandDiscountService.saveBrandDiscount(brandDiscount);
        return ResponseEntity.ok(newBrandDiscount);
    }
}
