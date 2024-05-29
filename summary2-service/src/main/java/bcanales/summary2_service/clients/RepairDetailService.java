package bcanales.summary2_service.clients;

import bcanales.summary2_service.dtos.RepairDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@FeignClient(name = "repair-service")
public interface RepairDetailService {
    @GetMapping("/api/repairDetails/")
    ArrayList<RepairDetailDTO> getRepairDetails();
}