package com.springframework.react;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employee API", description = "Employee CRUD Service", version = "1.0"))
public class SpringbootReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootReactApplication.class, args);
	}

}
