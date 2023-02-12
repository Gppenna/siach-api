package com.siach.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.siach.*") //packages for entity classes
@EnableJpaRepositories("com.siach.*")//packages for repo classes
public class ApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}



}
