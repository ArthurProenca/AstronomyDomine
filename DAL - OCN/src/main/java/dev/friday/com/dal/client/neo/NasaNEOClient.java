package dev.friday.com.dal.client.neo;

import dev.friday.com.dal.client.FeignClientConfig;
import dev.friday.com.dal.domain.entity.neo.dto.NeoBaseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "nasa-neo-client", url = "${nasa.neo.url}", configuration = FeignClientConfig.class)
@Component
public interface NasaNEOClient {

    @GetMapping
    NeoBaseDTO getNEOByDateInterval(@RequestParam String start_date, @RequestParam String end_date,
                                          @RequestParam String api_key);
}
