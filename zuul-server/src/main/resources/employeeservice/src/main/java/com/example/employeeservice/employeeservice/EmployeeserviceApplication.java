package com.example.employeeservice.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.starter.netflix.eureka.EnableEurekaClient;
/* import org.springframework.cloud.client.discovery.EnableDiscoveryClient; */

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class EmployeeserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeserviceApplication.class, args);
	}

}
