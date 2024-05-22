package bcanales.repairservice.clients;

import bcanales.repairservice.dtos.VehicleDTO;
import bcanales.repairservice.entity.RepairEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "discount-service")
public interface DiscountService {
    @PostMapping("/api/discounts/byRepairs")
    Double discountByRepairs(@RequestBody VehicleDTO vehicle);

    @PostMapping("/api/discounts/byAttentionDays")
    Double discountByAttentionDays(@RequestBody RepairEntity repair);
}
