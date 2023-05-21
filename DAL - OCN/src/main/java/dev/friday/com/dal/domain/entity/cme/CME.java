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
@Table(name = "T_CME")
@Builder
public class CME extends BaseEntity {
    @Id
    @Column(name = "UIDPK", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uidPk;

    @Column(name = "CME_CATALOG", nullable = false)
    private String cmeCatalog;

    @Column(name = "START_TIME", nullable = false)
    private Date startTime;

    @Column(name = "NOTE", nullable = false, length = 1024)
    private String note;

    @Column(name = "ACTIVITY_ID", nullable = false)
    private String activityId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CME_ANALYSES_UIDPK", nullable = false)
    private List<CMEAnalyses> cmeAnalyses;

}
