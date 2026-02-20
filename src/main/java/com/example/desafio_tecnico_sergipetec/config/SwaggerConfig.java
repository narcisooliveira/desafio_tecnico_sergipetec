package com.example.desafio_tecnico_sergipetec.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Desafio Técnico Sergipetec")
                        .description("Documentação da API do sistema de pedidos")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("- Narciso Oliveira")
                                .email("nrcsfo@gmail.com"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT"))
                );
    }
}