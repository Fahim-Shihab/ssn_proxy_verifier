package com.ibas.safetynet.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "OpenApi specification",
                version = "1.0",
                description = "OpenApi documentation",
                contact = @Contact(name = "Fahim", email = "gmail.com"),
                license = @License(name = "Licence name", url = "https://some-url.com"),
                termsOfService = "Terms of service"
        ),
        security = @SecurityRequirement(name = "bearerAuth")  // Global security requirement for all operations
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@Profile("dev")
public class OpenApiConfig {
}
