package com.template.springproject;

import com.template.springproject.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class BaseSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseSpringProjectApplication.class, args);
	}

}
