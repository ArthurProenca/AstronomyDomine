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
@Table(name = "T_CME_ANALYSES")
@Builder
public class CMEAnalyses extends BaseEntity {

    @Id
    @Column(name = "UIDPK", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uidPk;

    @Column(name = "TIME21_5")
    private Date time21_5;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    @Column(name = "HALF_ANGLE")
    private Double halfAngle;

    @Column(name = "SPEED")
    private Double speed;

    @Column(name = "TYPE")
    private Character type;

    @Column(name = "NOTE", length = 1024)
    private String note;

    @Column(name = "EXTERNAL_LINK", nullable = false)
    private String externalLink;

    @Column(name = "IS_MOST_ACCURATE", nullable = false)
    private Boolean isMostAccurate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CME_ANALYSES_UIDPK", nullable = false)
    private List<CMEEnlil> cmeEnlils;

}
