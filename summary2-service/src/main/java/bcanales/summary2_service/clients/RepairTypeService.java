package bcanales.summary2_service.clients;

import bcanales.summary2_service.dtos.RepairTypeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "repair-type-service")
public interface RepairTypeService {

    @GetMapping("/api/repairTypes/")
    List<RepairTypeDTO> getRepairTypes();
}