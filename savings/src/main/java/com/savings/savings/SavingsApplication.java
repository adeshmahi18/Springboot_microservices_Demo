package com.savings.savings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SavingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavingsApplication.class, args);
	}

}
