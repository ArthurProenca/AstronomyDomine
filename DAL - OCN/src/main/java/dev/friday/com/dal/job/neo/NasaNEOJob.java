package dev.friday.com.dal.job.neo;

import dev.friday.com.dal.client.neo.NasaNEOClient;
import dev.friday.com.dal.domain.entity.neo.dto.NeoDTO;
import dev.friday.com.dal.service.neo.NEOService;
import dev.friday.com.dal.utils.date.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Component
public class NasaNEOJob {

    @Value("${nasa.api.key}")
    private String nasaApiKey;

    private final NEOService neoService;

    private final NasaNEOClient nasaNEOClient;

   /* @Scheduled(cron = "0 0 * * * *")
    public void run() {
        log.info("NasaNEOJob started at [{}}", new Date());

        Map<String, List<NeoDTO>> neosBaseDTO = nasaNEOClient
                .getNEOByDateInterval(getStartDate(), getEndDate(), nasaApiKey)
                .getNear_earth_objects();

        Collection<List<NeoDTO>> neoDTOs = neosBaseDTO.values();
        for(List<NeoDTO> neoDTO : neoDTOs) {
            for(NeoDTO neo : neoDTO) {
                neoService.save(neo.toEntity());
            }
        }

        log.info("NasaNEOJob finished at [{}}", new Date());
    }*/

    private String getEndDate() {
        return getStartDate();
    }

    private String getStartDate() {
        return DateUtils.getTodayDateInUSAPattern();
    }
}
