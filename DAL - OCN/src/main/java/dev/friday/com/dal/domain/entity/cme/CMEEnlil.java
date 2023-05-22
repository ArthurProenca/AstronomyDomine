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
    private Double estimatedDuration;

    @Column(name = "IS_EARTH_GB", nullable = false)
    private Boolean isEarthGB;

    @Column(name = "EXTERNAL_LINK", nullable = false)
    private String externalLink;

    @Column(name = "RMIN_RE")
    private Double rmin_re;

    @Column(name = "KP_18")
    private Double kp_18;

    @Column(name = "KP_90")
    private Double kp_90;

    @Column(name = "KP_135")
    private Double kp_135;

    @Column(name = "KP_180")
    private Double kp_180;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CME_ENLIL_UIDPK")
    private List<CMEImpact> impacts;
}
