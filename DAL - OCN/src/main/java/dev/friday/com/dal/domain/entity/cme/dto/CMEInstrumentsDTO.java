package dev.friday.com.dal.domain.entity.cme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CMEInstrumentsDTO {
    private String displayName;
}
