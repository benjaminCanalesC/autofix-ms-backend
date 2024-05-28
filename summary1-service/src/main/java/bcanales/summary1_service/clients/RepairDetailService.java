package bcanales.summary1_service.clients;

import bcanales.summary1_service.dtos.RepairDetailDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.ArrayList;

@FeignClient(name = "repair-service")
public interface RepairDetailService {
    @GetMapping("/api/repairDetails/")
    ArrayList<RepairDetailDTO> getRepairDetails();
}
