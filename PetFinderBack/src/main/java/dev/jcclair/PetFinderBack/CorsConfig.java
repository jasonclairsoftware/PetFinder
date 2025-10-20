package dev.jcclair.PetFinderBack;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This configuration file will allow CORS security to allow traffic from teh front end environment.
 *
 * @author Jason Clair
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Initial configurations for CORS mappings.
     * @param registry - The register for Spring
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
