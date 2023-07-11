package dev.friday.com.dal.job.cme;


import dev.friday.com.dal.client.cme.NasaCMEClient;
import dev.friday.com.dal.service.cme.CMEService;
import dev.friday.com.dal.utils.date.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@Component
public class NasaCMEJob {

    private final NasaCMEClient nasaCMEClient;

    private final CMEService cmeService;

    @Value("${nasa.api.key}")
    private String nasaApiKey;

    /*@Scheduled(cron = "0 0 * * * *")
    public void run() {
        log.info("NasaCMEJob started at [{}}", new Date());
        nasaCMEClient.getCMEByDateInterval(getStartDate(), getEndDate(), nasaApiKey)
                .forEach(cmeDTO -> cmeService.save(cmeDTO.toEntity()));
        log.info("NasaCMEJob finished at [{}}", new Date());
    }*/

    private String getEndDate() {
        return getStartDate();
    }

    private String getStartDate() {
        return DateUtils.getTodayDateInUSAPattern();
    }
}
