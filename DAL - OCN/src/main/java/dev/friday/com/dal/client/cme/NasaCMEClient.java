package dev.friday.com.dal.client.cme;

import dev.friday.com.dal.domain.entity.cme.dto.CMEDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "nasa-cme", url = "${nasa.cme.url}")
@Component
public interface NasaCMEClient {

    @RequestMapping(method = RequestMethod.GET)
    List<CMEDTO> getCMEByDateInterval(@RequestParam String startDate, @RequestParam String endDate,
                                      @RequestParam String api_key);
}
