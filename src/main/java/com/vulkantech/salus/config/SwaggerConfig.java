package com.vulkantech.salus.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Salus")
                        .description("Sistema para gerenciamento de consultas em Unidades Básicas de Saúde")
                        .version("1.0.0"));
    }
}
