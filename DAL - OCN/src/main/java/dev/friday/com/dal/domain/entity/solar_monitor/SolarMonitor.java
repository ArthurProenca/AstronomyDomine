package dev.friday.com.dal.domain.entity.solar_monitor;

import dev.friday.com.dal.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "T_SOLAR_MONITOR")
@Builder
public class SolarMonitor extends BaseEntity {

    @Id
    @Column(name = "UIDPK", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uidPk;

    @Column(name = "URI")
    private String uri;

    @Column(name = "RELATIVE_DATE")
    private Date relativeDate;

    @Lob
    @JdbcTypeCode(Types.BINARY)
    @Column(name = "IMAGE", columnDefinition = "bytea")
    private byte[] image;
}
