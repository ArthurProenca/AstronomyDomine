package dev.friday.com.dal.repository.setting.definition;

import dev.friday.com.dal.domain.entity.setting.definition.SettingDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingDefinitionRepository extends JpaRepository<SettingDefinition, Long> {

    Optional<SettingDefinition> findSettingDefinitionBySettingIdentifier(String settingIdentifier);
}
