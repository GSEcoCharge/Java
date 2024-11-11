package br.com.example.ecocharge.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


public class SwaggerConfig{
    @Bean
    public OpenAPI swaggerConfig() {
        return new OpenAPI().info(new Info()
            .title("API CGenius")
        );
    }
}
