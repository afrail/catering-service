package com.catering.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

/**
 * @Project   candle
 * @Author    Md. Afrail Hossain
 * @Since     jan 21, 2024
 * @version   1.0.0
 */

@EnableScheduling
@SpringBootApplication
public class CateringService extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(CateringService.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(CateringService.class);
	}

}
