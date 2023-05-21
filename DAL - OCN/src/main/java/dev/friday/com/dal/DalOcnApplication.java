package dev.friday.com.dal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:.env")
public class DalOcnApplication {

	public static void main(String[] args) {
		SpringApplication.run(DalOcnApplication.class, args);
	}

}
