package bcanales.repairservice.clients;

import bcanales.repairservice.entity.RepairEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "brand-discount-service")
public interface BrandDiscountService {
    @PostMapping("/api/brandDiscounts/calculate")
    int calculateBrandDiscount(@RequestBody RepairEntity repair);
}
