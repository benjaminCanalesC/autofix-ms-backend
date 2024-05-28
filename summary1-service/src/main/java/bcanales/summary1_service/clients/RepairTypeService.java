package bcanales.summary1_service.clients;

import bcanales.summary1_service.dtos.RepairTypeDTO;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "repair-type-service")
public interface RepairTypeService {

    @GetMapping("/")
    List<RepairTypeDTO> getRepairTypes();
}