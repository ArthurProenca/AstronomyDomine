package dev.friday.com.dal.domain.entity.setting.definition;

import dev.friday.com.dal.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "T_SETTING_DEFINITION")
@Builder
public class SettingDefinition extends BaseEntity {
    @Id
    @Column(name = "UIDPK", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uidPk;

    @Column(name = "SETTING_VALUE")
    private String settingValue;

    @Column(name = "SETTING_NAME")
    private String settingName;

    @Column(name = "SETTING_IDENTIFIER")
    private String settingIdentifier;

    @Transient
    public Integer getSettingValueAsInteger() {
        return Integer.parseInt(settingValue);
    }
}
