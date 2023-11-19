package com.example.shield;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.example.shield")
public class ShieldApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShieldApplication.class, args);
	}

}
