package com.app.sysem;

import static com.app.sysem.model.Role.ADMIN;
import static com.app.sysem.model.Role.MANAGER;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.app.sysem.model.RegisterRequest;
import com.app.sysem.services.AuthenticationService;
import com.github.lalyos.jfiglet.FigletFont;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SysemSb3Application {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysemSb3Application.class);

	public static void main(String[] args) {
		SpringApplication.run(SysemSb3Application.class, args);
		try {
			String banner = "Sysem API";
			String asciiArt1 = FigletFont.convertOneLine(banner);
			LOGGER.info(banner);
			System.out.println(asciiArt1);
		} catch (IOException e) {
			LOGGER.error("Error {0}", e);
		}
	}

	@Bean
	CommandLineRunner commandLineRunner(AuthenticationService service) {
		return args -> {

			if(service.notExistUserRegister("admin@mail.com")){
				var admin = RegisterRequest.builder()
						.firstname("Admin")
						.lastname("Admin")
						.email("admin@mail.com")
						.password("orion1993$")
						.role(ADMIN)
						.build();
				System.out.println("Admin token: " + service.register(admin).getAccessToken());
			}

			if(service.notExistUserRegister("manager@mail.com")){
				var manager = RegisterRequest.builder()
						.firstname("Admin")
						.lastname("Admin")
						.email("manager@mail.com")
						.password("orion1993#")
						.role(MANAGER)
						.build();
				System.out.println("Manager token: " + service.register(manager).getAccessToken());
			}
		};
	}

}
