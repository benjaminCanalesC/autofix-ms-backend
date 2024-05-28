package bcanales.summary1_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryDTO {
    private String repairName;

    private int hatchbackCount;
    private int hatchbackAmount;

    private int suvCount;
    private int suvAmount;

    private int sedanCount;
    private int sedanAmount;

    private int pickupCount;
    private int pickupAmount;

    private int vanCount;
    private int vanAmount;

    private int totalCount;
    private int totalAmount;
}
