package com.example.hrplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
public class HrPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrPlatformApplication.class, args);
	}

}
