package com.ibas.safetynet;

import com.ibas.safetynet.common.ReadProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(ReadProps.class)
@EnableAsync
@EnableScheduling
public class SsnVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsnVerificationApplication.class, args);
	}

}
