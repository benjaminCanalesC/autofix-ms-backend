package bcanales.repairservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "repairDetail")
public class RepairDetailEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehiclePlate;
    private LocalDateTime repairDateTime;
    private int repairCost;

    private Long repairTypeId;

    @ManyToOne
    @JoinColumn(name = "repair")
    @JsonBackReference
    private RepairEntity repair;
}