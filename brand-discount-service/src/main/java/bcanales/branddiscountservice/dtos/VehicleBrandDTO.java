package bcanales.branddiscountservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleBrandDTO {
    private Long id;

    private String brand;
}
