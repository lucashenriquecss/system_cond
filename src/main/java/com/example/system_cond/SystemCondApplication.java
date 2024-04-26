package com.example.system_cond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJdbcAuditing
@SpringBootApplication
@EnableScheduling
public class SystemCondApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemCondApplication.class, args);
	}

}
