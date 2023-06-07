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

    @Scheduled(cron = "0 * * * * *")
    public void run() {
        log.info("SolarMonitorScrapperJob started at [{}}", new Date());
        Document document = Scrapper.getDocumentFromUri(getFormattedUrl());
        if(document == null) {
            return;
        }

        List<Element> elements = Scrapper.getElementFromDocument(document, "a");
        List<String> fdPngUris = getFdPngsUriFromElements(elements);

        for(String uri : fdPngUris) {
            solarMonitorService.save(SolarMonitor.builder()
                    .image(solarMonitorClient.getSolarMonitorData(uri))
                    .uri(solarMonitorUri + "data/" + uri)
                    .relativeDate(new Date())
                    .build());
        }

        log.info("SolarMonitorScrapperJob finished at [{}}", new Date());
    }
    private List<String> getFdPngsUriFromElements(final List<Element> elements) {
        List<String> fdPngsUriList = new ArrayList<>();
        for(Element element : elements) {
            if(element.attr("href").contains("fd")) {
                fdPngsUriList.add(getFormattedUrlWithPathVariable(element.attr("href")));
            }
        }
        return fdPngsUriList;
    }

    private String getFormattedUrl() {
        return String.format("%s/data/%s/%s/%s/pngs/shmi/",
                settingDefinitionService.getSettingValue("solar_monitor_url").getSettingValue(),
                DateUtils.getThisYear(),
                DateUtils.getThisMonth(),
                DateUtils.getThisDay());
    }

    private String getFormattedUrlWithPathVariable(String pathVariable) {
        return String.format("/%s/%s/%s/pngs/shmi/%s",
                DateUtils.getThisYear(),
                DateUtils.getThisMonth(),
                DateUtils.getThisDay(),
                pathVariable);
    }

}
