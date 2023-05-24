package dev.friday.com.dal.domain.entity.neo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NeoEstimatedDiameterDTO {
    private NeoDiameterRangeDTO kilometers;
    private NeoDiameterRangeDTO meters;
    private NeoDiameterRangeDTO miles;
    private NeoDiameterRangeDTO feet;
}
