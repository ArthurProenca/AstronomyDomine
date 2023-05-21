package dev.friday.com.dal.domain.entity.cme;

import dev.friday.com.dal.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "T_CME_ENLIL")
@Builder
public class CMEEnlil extends BaseEntity {

    @Id
    @Column(name = "UIDPK", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uidPk;

    @Column(name = "MODEL_COMPLETION_TIME")
    private Date modelCompletionTime;

    @Column(name = "AU")
    private Double au;

    @Column(name = "ESTIMATED_SHOCK_ARRIVAL_TIME", nullable = true)
    private Date estimatedShockArrivalTime;

    @Column(name = "ESTIMATED_DURATION", nullable = true)
    private Date estimatedDuration;

    @Column(name = "IS_EARTH_GB", nullable = false)
    private Boolean isEarthGB;

    @Column(name = "EXTERNAL_LINK", nullable = false)
    private String externalLink;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CME_IMPACT_UIDPK", nullable = false)
    private List<CMEImpact> impacts;
}
