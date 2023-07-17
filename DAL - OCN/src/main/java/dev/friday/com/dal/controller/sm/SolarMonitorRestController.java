package dev.friday.com.dal.controller.sm;

import dev.friday.com.dal.controller.BaseRestController;
import dev.friday.com.dal.service.solar_monitor.SolarMonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class SolarMonitorRestController extends BaseRestController {

    private final SolarMonitorService solarMonitorService;

    @GetMapping("/v1/solar-monitor")
    public ResponseEntity<?> getSolarMonitor(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(solarMonitorService.getSolarMonitorByISODate(date, true));
    }

    @GetMapping(value = "/v1/solar-monitor/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getSolarMonitorImage(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                     @RequestParam (required = false) int index) {
        return solarMonitorService.getSolarMonitorImages(date, index);
    }

}
