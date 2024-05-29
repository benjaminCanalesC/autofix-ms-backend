package bcanales.summary2_service.clients;

import bcanales.summary2_service.dtos.Summary1DTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@FeignClient(name = "summary1-service")
public interface Summary1Service {

    @GetMapping("/api/summary1/{month}/{year}")
    ArrayList<Summary1DTO> getSummary(@PathVariable("month") int month, @PathVariable("year") int year);
}