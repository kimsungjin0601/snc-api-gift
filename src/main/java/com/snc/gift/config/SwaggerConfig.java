package com.snc.gift.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
	
	private static final String API_NAME = "SNC-GIFT API";
	private static final String API_VERSION = "1.0.0";
	private static final String API_DESCRIPTION = "SNC-GIFT API 명세서";

	@Bean
    public OpenAPI openAPI(){
		SecurityScheme securityScheme = new SecurityScheme()
				.name("AccessToken")
				.type(SecurityScheme.Type.HTTP)
				.in(SecurityScheme.In.HEADER)
				.scheme("bearer")
				.bearerFormat("JWT");

		return new OpenAPI()
				.info(new Info().title(API_NAME).description(API_DESCRIPTION).version(API_VERSION))
				.security(Collections.singletonList(new SecurityRequirement().addList("AccessToken")))
				.components(new Components()
						.addSecuritySchemes("AccessToken", securityScheme)
						//.addSecuritySchemes("Channel", channelScheme)
				);
    }
}
