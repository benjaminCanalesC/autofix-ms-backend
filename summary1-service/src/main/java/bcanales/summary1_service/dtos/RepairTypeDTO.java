package bcanales.summary1_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairTypeDTO {
    private Long id;
    private String repairType;
    private String repairDescription;
    private int gasolineCost;
    private int dieselCost;
    private int hybridCost;
    private int electricCost;
}