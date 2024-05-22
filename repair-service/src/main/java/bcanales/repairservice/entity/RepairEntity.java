package bcanales.repairservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "repair")
public class RepairEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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