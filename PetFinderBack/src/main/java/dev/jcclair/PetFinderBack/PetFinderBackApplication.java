package dev.jcclair.PetFinderBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Spring Boot Web application
 */
@SpringBootApplication
public class PetFinderBackApplication {

    /**
     * Method that acts as the entry point for the application. Currently being handled by Spring Boot.
     * @param args - CLI arguments that can be used by Spring Boot
     */
	public static void main(String[] args) {
		SpringApplication.run(PetFinderBackApplication.class, args);
	}

}
