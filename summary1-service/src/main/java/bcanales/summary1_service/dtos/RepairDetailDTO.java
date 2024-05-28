package bcanales.summary1_service.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairDetailDTO {
    private Long id;

    private String vehiclePlate;
    private LocalDateTime repairDateTime;
    private int repairCost;

    private Long repairTypeId;

    private Long repairId;
}
