package com.mr.or_atp.or_atp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class OrAtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrAtpApplication.class, args);
	}

}
