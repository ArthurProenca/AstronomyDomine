package dev.friday.com.dal.repository.solar_monitor;

import dev.friday.com.dal.domain.entity.solar_monitor.SolarMonitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolarMonitorRepository extends JpaRepository<SolarMonitor, Long> {
}
