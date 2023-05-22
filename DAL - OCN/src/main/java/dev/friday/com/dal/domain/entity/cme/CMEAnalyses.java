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

    @Column(name = "TIME21_5", nullable = false)
    private Date time21_5;

    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

    @Column(name = "HALF_ANGLE", nullable = false)
    private Double halfAngle;

    @Column(name = "SPEED", nullable = false)
    private Double speed;

    @Column(name = "TYPE", nullable = false)
    private Character type;

    @Column(name = "NOTE", nullable = false, length = 1024)
    private String note;

    @Column(name = "EXTERNAL_LINK", nullable = false)
    private String externalLink;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CME_ENLILS_UIDPK", nullable = false)
    private List<CMEEnlil> cmeEnlils;

}
