package com.sermaluc.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Christian Misaico",
                        email = "christianmisaico@gmail.com",
                        url = "https://christianmisaico.com"
                ),
                description = "Sermaluc Demo - API",
                title = "Sermaluc Demo",
                version = "1.0.0"
        )
)
public class OpenApiConfiguration {
}
