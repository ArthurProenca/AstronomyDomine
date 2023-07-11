package dev.friday.com.dal.job.sm;

import dev.friday.com.dal.client.solar_monitor.SolarMonitorClient;
import dev.friday.com.dal.domain.entity.setting.definition.SettingDefinition;
import dev.friday.com.dal.domain.entity.solar_monitor.SolarMonitor;
import dev.friday.com.dal.service.setting.definition.SettingDefinitionService;
import dev.friday.com.dal.service.solar_monitor.SolarMonitorService;
import dev.friday.com.dal.utils.date.DateUtils;
import dev.friday.com.dal.utils.scrapping.Scrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class SolarMonitorScrapperJob {

    private final SettingDefinitionService settingDefinitionService;

    private final SolarMonitorService solarMonitorService;

    private final SolarMonitorClient solarMonitorClient;

    @Value("${solar-monitor.uri}")
    private String solarMonitorUri;

    @Scheduled(cron = "0 0 * * * *")
    public void run() {
        log.info("SolarMonitorScrapperJob started at [{}}", new Date());
        List<String> fdPngUris = solarMonitorService.getSolarMonitorImageUris();

        for(String uri : fdPngUris) {
            solarMonitorService.save(SolarMonitor.builder()
                    .image(solarMonitorClient.getSolarMonitorData(uri))
                    .uri(solarMonitorUri + "data/" + uri)
                    .relativeDate(DateUtils.getTodayDateInUSAPatternWithTime())
                    .build());
        }

        log.info("SolarMonitorScrapperJob finished at [{}}", new Date());
    }

}
