package bcanales.discountservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "repair-service", url = "http://repair-service")
public interface RepairService {
    @GetMapping("/api/amountByVehicleId/{id}")
    int getRepairsAmountByVehicleId(@PathVariable Long id);
}