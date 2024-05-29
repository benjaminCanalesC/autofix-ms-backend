package bcanales.summary1_service.clients;

import bcanales.summary1_service.dtos.RepairTypeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "repair-type-service")
public interface RepairTypeService {

    @GetMapping("/api/repairTypes/")
    List<RepairTypeDTO> getRepairTypes();
}