package dev.friday.com.dal.domain.entity.neo;

import dev.friday.com.dal.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "T_NEO_DATA")
@Builder
public class NeoData extends BaseEntity {

    @Id
    @Column(name = "UIDPK", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uidPk;

    @Column(name = "CLOSE_APPROACH_DATE")
    private String closeApproachDate;

    @Column(name = "ORBITING_BODY")
    private String orbitingBody;

    @Column(name = "RELATIVE_VELOCITY")
    private Double relativeVelocity;

    @Column(name = "MISS_DISTANCE")
    private Double missDistance;
}
