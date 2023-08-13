package com.pareeksha.blackcat.portman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.pareeksha.blackcat"})
@EnableScheduling
public class PortmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortmanApplication.class, args);
	}

}
