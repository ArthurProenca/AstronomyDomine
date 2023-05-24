package dev.friday.com.dal.domain.entity.neo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NeoMissDistanceDTO {
    private String astronomical;
    private String lunar;
    private String kilometers;
    private String miles;
}
