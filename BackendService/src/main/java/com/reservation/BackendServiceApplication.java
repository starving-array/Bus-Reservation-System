package com.reservation;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BackendServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendServiceApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.reservation"))
				.build().apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("Bus Reservation Hybrid System", "Sample api", "1.0", "Freee",
				new springfox.documentation.service.Contact("hilarious group", "#", "a@b.com"), "API l",
				"http://javabrains.io", Collections.emptyList());
	}
}
