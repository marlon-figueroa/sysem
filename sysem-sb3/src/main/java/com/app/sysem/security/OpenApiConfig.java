package com.app.sysem.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
		contact = @Contact(
				name = "Marlon E. Figueroa", 
				email = "marlon.f.1993@gmail.com", 
				url = "https://sysem.xyz"), 
				description = "Api Rest para administrar negocios de distribucion", 
				title = "Sysem API", 
				version = "1.0", 
				license = @License(
						name = "Licence name", 
						url = "https://some-url.com"
				), 
				termsOfService = "Terms of service"), 
	servers = {
		@Server(description = "Local ENV", url = "http://localhost:8081"),
		@Server(description = "PROD ENV", url = "https://sysem.xyz") 
	}, 
	security = {
			@SecurityRequirement(name = "bearerAuth") })
@SecurityScheme(
		name = "bearerAuth", 
		description = "JWT auth description", 
		scheme = "bearer", 
		type = SecuritySchemeType.HTTP, 
		bearerFormat = "JWT", 
		in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {

}