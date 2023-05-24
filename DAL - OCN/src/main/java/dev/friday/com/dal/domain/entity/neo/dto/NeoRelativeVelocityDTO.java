package dev.friday.com.dal.domain.entity.neo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NeoRelativeVelocityDTO {
    private String kilometers_per_second;
    private String kilometers_per_hour;
    private String miles_per_hour;
}
