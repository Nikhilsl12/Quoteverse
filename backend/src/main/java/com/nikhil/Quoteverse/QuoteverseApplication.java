package com.nikhil.Quoteverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class QuoteverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuoteverseApplication.class, args);
	}

}
