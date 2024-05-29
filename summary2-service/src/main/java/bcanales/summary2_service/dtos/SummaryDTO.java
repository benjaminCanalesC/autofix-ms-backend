package bcanales.summary2_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryDTO {
    private String repairName;

    private int actualMonthCount;
    private int actualMonthAmount;
    private int actualMonthCountDelta;
    private int actualMonthAmountDelta;

    private int previousMonthCount;
    private int previousMonthAmount;
    private int previousMonthCountDelta;
    private int previousMonthAmountDelta;

    private int secondPreviousMonthCount;
    private int secondPreviousMonthAmount;
}