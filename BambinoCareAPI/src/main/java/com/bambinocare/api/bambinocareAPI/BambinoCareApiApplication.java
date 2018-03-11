package com.bambinocare.api.bambinocareAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

@ComponentScan
@SpringBootApplication
public class BambinoCareApiApplication extends SpringBootServletInitializer implements WebApplicationInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(BambinoCareApiApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BambinoCareApiApplication.class);
	}
}
