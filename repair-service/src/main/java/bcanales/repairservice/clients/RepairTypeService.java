package bcanales.repairservice.clients;

import bcanales.repairservice.dtos.RepairTypeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "repair-type-service")
public interface RepairTypeService {
    @GetMapping("/api/repairTypes/{id}")
    Optional<RepairTypeDTO> getRepairTypeById(@PathVariable("id") Long id);
}
