package com.mini_project.books_catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(description = " documentation", title = "My API", version = "2.0.2"))

public class BooksCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksCatalogApplication.class, args);
	}

}
