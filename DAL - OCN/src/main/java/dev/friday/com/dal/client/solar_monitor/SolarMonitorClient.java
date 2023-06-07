package dev.friday.com.dal.client.solar_monitor;

import dev.friday.com.dal.client.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "solar-monitor-client", url = "${solar-monitor.uri}", configuration = FeignClientConfig.class)
@Component
public interface SolarMonitorClient {

    @GetMapping("/data/{uri}")
    byte[] getSolarMonitorData(@PathVariable String uri);
}
