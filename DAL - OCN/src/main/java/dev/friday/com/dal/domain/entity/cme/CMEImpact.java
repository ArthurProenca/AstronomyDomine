package dev.friday.com.dal.domain.entity.cme;

import dev.friday.com.dal.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "T_CME_IMPACT")
@Builder
public class CMEImpact extends BaseEntity {

    @Id
    @Column(name = "UIDPK", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uidPk;

    @Column(name = "IS_GLANCING_BLOW", nullable = false)
    private Boolean isGlancingBlow;

    @Column(name = "LOCATION", nullable = false)
    private String location;

    @Column(name = "ARRIVAL_TIME", nullable = false)
    private Date arrivalTime;
}
