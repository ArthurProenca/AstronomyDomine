package dev.friday.com.dal.domain.entity.neo.dto;

import dev.friday.com.dal.domain.entity.neo.NeoData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NeoCloseApproachDataDTO {
    private String close_approach_date;
    private String close_approach_date_full;
    private Long epoch_date_close_approach;
    private NeoRelativeVelocityDTO relative_velocity;
    private NeoMissDistanceDTO miss_distance;
    private String orbiting_body;

    public NeoData toEntity() {
        return NeoData.builder()
                .closeApproachDate(close_approach_date)
                .orbitingBody(orbiting_body)
                .relativeVelocity(Double.valueOf(relative_velocity == null ? null : relative_velocity.getKilometers_per_hour()))
                .missDistance(Double.valueOf(miss_distance.getKilometers()))
                .build();
    }
}
