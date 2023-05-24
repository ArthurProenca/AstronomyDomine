package dev.friday.com.dal.domain.entity.neo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.friday.com.dal.domain.entity.neo.Neo;
import dev.friday.com.dal.utils.date.DateUtils;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NeoDTO {

    private String id;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date observationDate;
    private String neo_reference_id;
    private String name;
    private String nasa_jpl_url;
    private double absolute_magnitude_h;
    private NeoEstimatedDiameterDTO estimated_diameter;
    private Boolean is_potentially_hazardous_asteroid;
    private List<NeoCloseApproachDataDTO> close_approach_data;
    private Boolean is_sentry_object;

    public Neo toEntity() {
        return Neo.builder()
                .absoluteMagnitudeH(absolute_magnitude_h)
                .estimatedDiameterMax(estimated_diameter == null ? null : estimated_diameter.getKilometers().getEstimated_diameter_max())
                .estimatedDiameterMin(estimated_diameter == null ? null : estimated_diameter.getKilometers().getEstimated_diameter_min())
                .id(id)
                .observationDate(close_approach_data == null ? null : DateUtils.fromString(close_approach_data.stream().findFirst().get().getClose_approach_date()))
                .isPotentiallyHazardous(is_potentially_hazardous_asteroid)
                .isSentryObject(is_sentry_object)
                .name(name)
                .nasaJplUrl(nasa_jpl_url)
                .neoData(close_approach_data == null? null : close_approach_data.stream().map(NeoCloseApproachDataDTO::toEntity).toList())
                .build();
    }
}
