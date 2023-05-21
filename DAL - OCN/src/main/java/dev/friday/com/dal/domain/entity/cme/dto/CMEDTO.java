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
public class CMEDTO {
    private String activityID;
    private String catalog;
    private Date startTime;
    private String sourceLocation;
    private Integer activeRegionNum;
    private String link;
    private String note;
    private List<CMEInstrumentsDTO> instruments;
    private List<CMEAnalysesDTO> cmeAnalyses;

    public CME toEntity() {
        return CME.builder()
                .cmeCatalog(this.catalog)
                .startTime(this.startTime)
                .activityId(this.activityID)
                .note(this.note)
                .cmeAnalyses(cmeAnalyses.stream().map(CMEAnalysesDTO::toEntity).toList())
                .build();
    }

    public static CMEDTO fromEntity(final CME cme) {
        return CMEDTO.builder()
                .catalog(cme.getCmeCatalog())
                .startTime(cme.getStartTime())
                .activityID(cme.getActivityId())
                .note(cme.getNote())
                .cmeAnalyses(cme.getCmeAnalyses().stream().map(CMEAnalysesDTO::fromEntity).toList())
                .build();
    }
}
