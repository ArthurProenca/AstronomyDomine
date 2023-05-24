package dev.friday.com.dal.domain.entity.neo;


import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "T_NEO")
@Builder
public class Neo extends BaseEntity {

    @Id
    @Column(name = "UIDPK", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uidPk;

    @Column(name = "NEO_ID")
    private String id;

    @Column(name = "NEO_REFERENCE_ID")
    private String name;

    @Column(name = "OBSERVATION_DATE")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date observationDate;

    @Column(name ="NEO_NASA_JPL_URL")
    private String nasaJplUrl;

    @Column(name = "NEO_ABSOLUTE_MAGNITUDE_H")
    private Double absoluteMagnitudeH;

    @Column(name = "IS_SENTRY_OBJECT")
    private boolean isSentryObject;

    @Column(name = "IS_POTENTIALLY_HAZARDOUS")
    private boolean isPotentiallyHazardous;

    @Column(name = "NEO_ESTIMATED_DIAMETER_MIN")
    private Double estimatedDiameterMin;

    @Column(name = "NEO_ESTIMATED_DIAMETER_MAX")
    private Double estimatedDiameterMax;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "NEO_UIDPK", nullable = false)
    private List<NeoData> neoData;

}
