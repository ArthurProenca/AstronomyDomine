package dev.friday.com.dal.service.setting.definition;

import dev.friday.com.dal.domain.entity.setting.definition.SettingDefinition;
import dev.friday.com.dal.repository.setting.definition.SettingDefinitionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SettingDefinitionService {
    private final SettingDefinitionRepository settingDefinitionRepository;

    public SettingDefinition getSettingValue(final String key) {
        return settingDefinitionRepository.findSettingDefinitionBySettingIdentifier(key).orElse(null);
    }

    public void save(final SettingDefinition settingDefinition) {
        if(settingDefinitionRepository.findSettingDefinitionBySettingIdentifier(settingDefinition
                .getSettingIdentifier()).isEmpty()) {
            log.info("SettingDefinition with key [{}] already exists", settingDefinition.getSettingIdentifier());
            return;
        }
        settingDefinitionRepository.save(settingDefinition);
    }
}
