package com.ibas.safetynet;

import com.ibas.safetynet.common.ReadProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ReadProps.class)
public class SsnVerificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsnVerificationApplication.class, args);
	}

}
