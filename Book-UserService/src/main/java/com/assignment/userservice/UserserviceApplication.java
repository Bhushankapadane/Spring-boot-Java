package com.assignment.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	@Bean
	public WebClient.Builder getWebClientBuilder() {
		return WebClient.builder();
	}
	
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/*"))
				.apis(RequestHandlerSelectors.basePackage("com.assignment.userservice"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		// TODO Auto-generated method stub
		ApiInfo apiInfo= new ApiInfo("User Service API", "Demo on Micro Services", "1.0", "Team3@bosch.com", "Team 3",null, null);
		return apiInfo;
	}

}
