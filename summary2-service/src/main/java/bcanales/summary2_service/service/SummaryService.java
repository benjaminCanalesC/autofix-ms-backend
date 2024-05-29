package bcanales.summary2_service.service;

import bcanales.summary2_service.clients.RepairTypeService;
import bcanales.summary2_service.clients.Summary1Service;
import bcanales.summary2_service.dtos.RepairTypeDTO;
import bcanales.summary2_service.dtos.Summary1DTO;
import bcanales.summary2_service.dtos.SummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SummaryService {
    @Autowired
    private Summary1Service summary1Service;
    @Autowired
    private RepairTypeService repairTypeService;

    public ArrayList<SummaryDTO> getSummary(int month, int year) {
        List<RepairTypeDTO> repairTypes = repairTypeService.getRepairTypes();

        ArrayList<SummaryDTO> summaries = new ArrayList<>();

        int previousMonth;
        int previousYear;

        int secondPreviousMonth;
        int secondPreviousYear;

        if (month == 1 ) {
            previousMonth = 12;
            previousYear = year - 1;
        } else {
            previousMonth = month - 1;
            previousYear = year;
        }

        if (previousMonth == 1) {
            secondPreviousMonth = 12;
            secondPreviousYear = previousYear - 1;
        } else {
            secondPreviousMonth = previousMonth - 1;
            secondPreviousYear = previousYear;
        }

        ArrayList<Summary1DTO> summaryActualMonth = summary1Service.getSummary(month, year);
        ArrayList<Summary1DTO> summaryPreviousMonth = summary1Service.getSummary(previousMonth, previousYear);
        ArrayList<Summary1DTO> summarySecondPreviousMonth = summary1Service.getSummary(secondPreviousMonth, secondPreviousYear);

        for (RepairTypeDTO repairType : repairTypes) {
            String repairName = repairType.getRepairType();

            System.out.println(repairName);

            int secondPreviousMonthCount = 0, secondPreviousMonthAmount = 0;
            for (Summary1DTO summaryData : summarySecondPreviousMonth) {
                if (Objects.equals(repairName, summaryData.getRepairName())) {
                    secondPreviousMonthCount = summaryData.getTotalCount();
                    secondPreviousMonthAmount = summaryData.getTotalAmount();
                }
            }

            int previousMonthCount = 0, previousMonthAmount = 0;
            for (Summary1DTO summaryData : summaryPreviousMonth) {
                if (Objects.equals(repairName, summaryData.getRepairName())) {
                    previousMonthCount = summaryData.getTotalCount();
                    previousMonthAmount = summaryData.getTotalAmount();
                }
            }

            int actualMonthCount = 0, actualMonthAmount = 0;
            for (Summary1DTO summaryData : summaryActualMonth) {
                if (Objects.equals(repairName, summaryData.getRepairName())) {
                    actualMonthCount = summaryData.getTotalCount();
                    actualMonthAmount = summaryData.getTotalAmount();
                }
            }

            int previousMonthCountDelta = 0;
            if (secondPreviousMonthCount != 0) {
                previousMonthCountDelta = (int)(((double) (previousMonthCount - secondPreviousMonthCount) / secondPreviousMonthCount) * 100);
            } else {
                previousMonthCountDelta = previousMonthCount * 100;
            }

            int previousMonthAmountDelta = 0;
            if (secondPreviousMonthAmount != 0) {
                previousMonthAmountDelta = (int)(((double) (previousMonthAmount - secondPreviousMonthAmount) / secondPreviousMonthAmount) * 100);
            } else {
                previousMonthAmountDelta = previousMonthAmount * 100;
            }

            int actualMonthCountDelta = 0;
            if (previousMonthCount != 0) {
                actualMonthCountDelta = (int)(((double) (actualMonthCount - previousMonthCount) / previousMonthCount) * 100);
            } else {
                actualMonthCountDelta = actualMonthCount * 100;
            }


            int actualMonthAmountDelta = 0;
            if (previousMonthAmount != 0) {
                actualMonthAmountDelta = (int)(((double) (actualMonthAmount - previousMonthAmount) / previousMonthAmount) * 100);
            } else {
                actualMonthAmountDelta = actualMonthAmount * 100;
            }

            summaries.add(new SummaryDTO(repairName, actualMonthCount, actualMonthAmount, actualMonthCountDelta, actualMonthAmountDelta, previousMonthCount,
                    previousMonthAmount, previousMonthCountDelta, previousMonthAmountDelta, secondPreviousMonthCount, secondPreviousMonthAmount));
        }

        return summaries;
    }
}
