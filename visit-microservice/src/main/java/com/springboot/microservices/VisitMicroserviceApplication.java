package com.springboot.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VisitMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitMicroserviceApplication.class, args);
	}

}
