package dev.friday.com.dal.domain.entity.cme.dto;

import dev.friday.com.dal.domain.entity.cme.CMEEnlil;
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
public class CMEEnlilDTO {
    private Date modelCompletionTime;
    private Double au;
    private Date estimatedShockArrivalTime;
    private Double estimatedDuration;
    private Double rmin_re;
    private Double kp_18;
    private Double kp_90;
    private Double kp_135;
    private Double kp_180;
    private Boolean isEarthGB;
    private String link;
    private List<CMEImpactDTO> impactList;
    private List<String> cmeIDs;

    public CMEEnlil toEntity() {
        return CMEEnlil.builder()
                .modelCompletionTime(this.modelCompletionTime)
                .au(this.au)
                .isEarthGB(this.isEarthGB)
                .externalLink(this.link)
                .estimatedShockArrivalTime(this.estimatedShockArrivalTime)
                .estimatedDuration(this.estimatedDuration)
                .rmin_re(this.rmin_re)
                .kp_18(this.kp_18)
                .kp_90(this.kp_90)
                .kp_135(this.kp_135)
                .kp_180(this.kp_180)
                .impacts(impactList == null ? null : impactList.stream().map(CMEImpactDTO::toEntity).toList())
                .build();
    }

    public static CMEEnlilDTO fromEntity(final CMEEnlil cmeEnlil) {
        return CMEEnlilDTO.builder()
                .au(cmeEnlil.getAu())
                .isEarthGB(cmeEnlil.getIsEarthGB())
                .link(cmeEnlil.getExternalLink())
                .modelCompletionTime(cmeEnlil.getModelCompletionTime())
                .impactList(cmeEnlil.getImpacts().stream().map(CMEImpactDTO::fromEntity).toList())
                .build();
    }
}
