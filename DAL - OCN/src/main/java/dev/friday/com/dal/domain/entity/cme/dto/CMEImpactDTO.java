package dev.friday.com.dal.domain.entity.cme.dto;

import dev.friday.com.dal.domain.entity.cme.CMEImpact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CMEImpactDTO {
    private Boolean isGlancingBlow;
    private String location;
    private Date arrivalTime;

    public CMEImpact toEntity() {
        return CMEImpact.builder()
                .isGlancingBlow(this.isGlancingBlow)
                .location(this.location)
                .arrivalTime(this.arrivalTime)
                .build();
    }

    public static CMEImpactDTO fromEntity(final CMEImpact cmeImpact) {
        return CMEImpactDTO.builder()
                .isGlancingBlow(cmeImpact.getIsGlancingBlow())
                .location(cmeImpact.getLocation())
                .arrivalTime(cmeImpact.getArrivalTime())
                .build();
    }
}
