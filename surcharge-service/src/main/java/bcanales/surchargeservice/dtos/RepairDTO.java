package bcanales.surchargeservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairDTO {
    private Long id;

    private int repairCost;
    private int baseRepairCost;
    private int discount;
    private int surcharge;
    private int iva;
    private boolean bonusDiscount;

    private LocalDateTime entryDateTime;

    private LocalDateTime exitDateTime;

    private LocalDateTime pickupDateTime;

    private Long repairTypeId;

    private Long vehicleId;
}