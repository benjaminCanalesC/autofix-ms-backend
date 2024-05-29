package bcanales.summary2_service.controller;

import bcanales.summary2_service.dtos.SummaryDTO;
import bcanales.summary2_service.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/summary2")
@CrossOrigin("*")
public class SummaryController {
    @Autowired
    private SummaryService summaryService;
    @GetMapping("/{month}/{year}")
    public ResponseEntity<ArrayList<SummaryDTO>> getSummary(@PathVariable int month, @PathVariable int year) {
        ArrayList<SummaryDTO> summaries = summaryService.getSummary(month, year);
        return  ResponseEntity.ok(summaries);
    }
}
