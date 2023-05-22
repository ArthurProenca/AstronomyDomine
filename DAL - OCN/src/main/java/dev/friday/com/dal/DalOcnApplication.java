package dev.friday.com.dal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@PropertySource("classpath:.env")
@EnableFeignClients
@EnableScheduling
public class DalOcnApplication {

	public static void main(String[] args) {
		SpringApplication.run(DalOcnApplication.class, args);
	}

}
