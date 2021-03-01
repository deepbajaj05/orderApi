package com.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;

/**
 * Spring boot Main App!
 *
 */
@SpringBootApplication(exclude = ErrorWebFluxAutoConfiguration.class)

public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
