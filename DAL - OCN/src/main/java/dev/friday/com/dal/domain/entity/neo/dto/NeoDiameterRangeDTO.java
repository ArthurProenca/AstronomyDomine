package dev.friday.com.dal.domain.entity.neo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NeoDiameterRangeDTO {
    private double estimated_diameter_min;
    private double estimated_diameter_max;
}
