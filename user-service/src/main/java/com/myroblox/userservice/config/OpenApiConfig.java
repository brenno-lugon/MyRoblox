package com.myroblox.userservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI userServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MyRoblox User Service API")
                        .description("API responsável pelo gerenciamento de usuários da plataforma MyRoblox.")
                        .version("1.0.0"));
    }
}
