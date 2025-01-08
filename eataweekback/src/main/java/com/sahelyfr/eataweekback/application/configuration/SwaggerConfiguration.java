package com.sahelyfr.eataweekback.application.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfiguration {

  @Bean
  GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
        .group("public-apis")
        .pathsToMatch("/**")
        .build();
  }

  @Bean
  OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new io.swagger.v3.oas.models.info.Info().title("Eataweek API").version("v0.0.1"))
        .addSecurityItem(new SecurityRequirement().addList("bearer-jwt"))
        .components(new Components()
            .addSecuritySchemes("bearerAuth", new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")));
  }
}
