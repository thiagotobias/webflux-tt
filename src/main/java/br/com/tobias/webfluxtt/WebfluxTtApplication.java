package br.com.tobias.webfluxtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@EnableWebFlux
@OpenAPIDefinition(info = @Info(title = "Client API",
		           version = "1.0.0",
		description = "Documentation APIs v1.0.0"
))
@SpringBootApplication
public class WebfluxTtApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxTtApplication.class, args);
	}

}
