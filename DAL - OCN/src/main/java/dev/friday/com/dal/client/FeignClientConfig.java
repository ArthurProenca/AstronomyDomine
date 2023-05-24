package dev.friday.com.dal.client;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeignClientConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
