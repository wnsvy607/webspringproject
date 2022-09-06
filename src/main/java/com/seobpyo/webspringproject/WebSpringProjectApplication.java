package com.seobpyo.webspringproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebSpringProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(WebSpringProjectApplication.class, args);

	}

}
