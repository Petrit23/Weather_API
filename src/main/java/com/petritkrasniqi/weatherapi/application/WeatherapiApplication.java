package com.petritkrasniqi.weatherapi.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.petritkrasniqi.weatherapi")
@EntityScan("com.petritkrasniqi.weatherapi.entity")
@EnableJpaRepositories("com.petritkrasniqi.weatherapi.repository")
public class WeatherapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherapiApplication.class, args);
	}

}
