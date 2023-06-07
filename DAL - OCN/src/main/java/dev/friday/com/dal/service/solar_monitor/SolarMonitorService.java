package dev.friday.com.dal.service.solar_monitor;

import dev.friday.com.dal.domain.entity.solar_monitor.SolarMonitor;
import dev.friday.com.dal.repository.solar_monitor.SolarMonitorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SolarMonitorService {

    private final SolarMonitorRepository solarMonitorRepository;

    public void save(final SolarMonitor solarMonitor) {
        solarMonitorRepository.save(solarMonitor);
    }
}
