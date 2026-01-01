package com.example.eureka_server_domp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerDompApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerDompApplication.class, args);
	}

}
