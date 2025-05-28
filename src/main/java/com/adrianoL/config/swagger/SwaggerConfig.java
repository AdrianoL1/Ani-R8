package com.adrianoL.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Ani-R8 API")
                        .version("V1")
                        .description("A REST API that allows users to manage their personal anime lists, inspired by MyAnimeList.")
                        .license(new License()
                                .name("MIT License")
                                .url("https://choosealicense.com/licenses/mit/")
                        )
                )
                .components(new Components()
                        .addSecuritySchemes("bearer-key", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                );
    }
}
