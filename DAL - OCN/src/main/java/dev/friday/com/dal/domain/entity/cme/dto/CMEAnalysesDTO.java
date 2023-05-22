package dev.friday.com.dal.domain.entity.cme.dto;

import dev.friday.com.dal.domain.entity.cme.CME;
import dev.friday.com.dal.domain.entity.cme.CMEAnalyses;
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
public class CMEAnalysesDTO {
    private Date time21_5;
    private Double latitude;
    private Double longitude;
    private Double halfAngle;
    private Double speed;
    private String type;
    private Boolean isMostAccurate;
    private String note;
    private Integer levelOfData;
    private String link;
    private List<CMEEnlilDTO> enlilList;

    public CMEAnalyses toEntity() {
        return CMEAnalyses.builder()
                .time21_5(this.time21_5)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .halfAngle(this.halfAngle)
                .speed(this.speed)
                .type(this.type.charAt(0))
                .note(this.note)
                .externalLink(this.link)
                .cmeEnlils(enlilList == null ? null : enlilList.stream().map(CMEEnlilDTO::toEntity).toList())
                .build();
    }

    public static CMEAnalysesDTO fromEntity(final CMEAnalyses cmeAnalyses) {
        return CMEAnalysesDTO.builder()
                .time21_5(cmeAnalyses.getTime21_5())
                .latitude(cmeAnalyses.getLatitude())
                .longitude(cmeAnalyses.getLongitude())
                .halfAngle(cmeAnalyses.getHalfAngle())
                .speed(cmeAnalyses.getSpeed())
                .type(cmeAnalyses.getType().toString())
                .note(cmeAnalyses.getNote())
                .link(cmeAnalyses.getExternalLink())
                .enlilList(cmeAnalyses.getCmeEnlils().stream().map(CMEEnlilDTO::fromEntity).toList())
                .build();
    }
}
