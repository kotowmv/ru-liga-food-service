package ru.liga.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return openApi -> {
            openApi.addSecurityItem(new SecurityRequirement().addList("Bearer authentication"));
            openApi.components(new Components().addSecuritySchemes("Bearer authentication",
                    new SecurityScheme()
                            .name("Bearer authentication")
                            .bearerFormat("JWT")
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")));
        };
    }

}
