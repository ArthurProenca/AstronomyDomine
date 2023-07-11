package dev.friday.com.dal.service.solar_monitor;

import dev.friday.com.dal.domain.entity.solar_monitor.SolarMonitor;
import dev.friday.com.dal.repository.solar_monitor.SolarMonitorRepository;
import dev.friday.com.dal.service.setting.definition.SettingDefinitionService;
import dev.friday.com.dal.utils.date.DateUtils;
import dev.friday.com.dal.utils.scrapping.Scrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SolarMonitorService {

    private final SolarMonitorRepository solarMonitorRepository;

    private final SettingDefinitionService settingDefinitionService;

    @Value("${solar-monitor.uri}")
    private String solarMonitorUri;

    public void save(final SolarMonitor solarMonitor) {
        solarMonitorRepository.save(solarMonitor);
    }
    public ResponseEntity<?> getSolarMonitorByISODate(LocalDate date) {
        List<String> formattedUris = new ArrayList<>();
        int year = date.getYear();
        String month = DateUtils.getMonthAsString(date.getMonthValue());
        String day = DateUtils.getDayAsString(date.getDayOfMonth());

        Document document = Scrapper.getDocumentFromUri(getFormattedUrlByDate(year, month, day));

        List<Element> elements = Scrapper.getElementFromDocument(document, "a");
        List<String> fdPngUris = getFdPngsUriFromElements(elements, year, month, day);

        for(String uri : fdPngUris) {
            formattedUris.add(solarMonitorUri + "data/" + uri);
        }

        return ResponseEntity.ok(formattedUris);
    }

    public List<String> getSolarMonitorImageUris() {
        Document document = Scrapper.getDocumentFromUri(getFormattedUrl());

        List<Element> elements = Scrapper.getElementFromDocument(document, "a");
        return getFdPngsUriFromElements(elements);
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

    private List<String> getFdPngsUriFromElements(final List<Element> elements, int year, String month, String day) {
        List<String> fdPngsUriList = new ArrayList<>();
        for(Element element : elements) {
            if(element.attr("href").contains("fd")) {
                fdPngsUriList.add(getFormattedUrlWithPathVariableAndDate(element.attr("href"), year, month, day));
            }
        }
        return fdPngsUriList;
    }

    private String getFormattedUrlWithPathVariableAndDate(String pathVariable, int year, String month, String day) {
        return String.format("/%s/%s/%s/pngs/shmi/%s",
                year,
                month,
                day,
                pathVariable);
    }

    private String getFormattedUrlWithPathVariable(String pathVariable) {
        return String.format("/%s/%s/%s/pngs/shmi/%s",
                DateUtils.getThisYear(),
                DateUtils.getThisMonth(),
                DateUtils.getThisDay(),
                pathVariable);
    }

    private String getFormattedUrlByDate(int year, String month, String day) {
        return String.format("%s/data/%s/%s/%s/pngs/shmi/",
                settingDefinitionService.getSettingValue("solar_monitor_url").getSettingValue(),
                year, month, day);
    }

    private String getFormattedUrl() {
        return String.format("%s/data/%s/%s/%s/pngs/shmi/",
                settingDefinitionService.getSettingValue("solar_monitor_url").getSettingValue(),
                DateUtils.getThisYear(),
                DateUtils.getThisMonth(),
                DateUtils.getThisDay());
    }
}
