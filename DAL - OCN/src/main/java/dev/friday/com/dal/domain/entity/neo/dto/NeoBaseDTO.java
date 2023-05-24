package dev.friday.com.dal.domain.entity.neo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NeoBaseDTO {
    private Map<String, List<NeoDTO>> near_earth_objects;
}
