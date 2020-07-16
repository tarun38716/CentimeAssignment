package com.centime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServicethreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicethreeApplication.class, args);
	}

}
