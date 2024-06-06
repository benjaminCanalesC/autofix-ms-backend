package bcanales.repairservice.dtos;

import bcanales.repairservice.entity.RepairDetailEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveRepairDTO {
    private String vehiclePlate;
    private boolean bonusDiscount;
    private LocalDateTime entryDateTime;
    @OneToMany(mappedBy = "repair", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<RepairDetailEntity> repairDetails;
}
