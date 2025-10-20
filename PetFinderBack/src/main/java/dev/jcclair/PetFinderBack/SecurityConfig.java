package dev.jcclair.PetFinderBack;

import jakarta.ws.rs.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Security configurations for Spring. This is used to be overridden to allow CORS to operate accordingly.
 *
 * @author Jason Clair
 */
@Configuration
public class SecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource; // Inject your CORS bean

    /**
     * Overloaded CTOR used to handle traffic security
     *
     * @param corsConfigurationSource - CORS security authentication
     */
    public SecurityConfig(CorsConfigurationSource corsConfigurationSource) {
        this.corsConfigurationSource = corsConfigurationSource;
    }

    /**
     * Used to create the filter chain for Spring Security
     *
     * @param http - HTTP Security for Spring
     * @return - Filterchain for Spring
     * @throws Exception - Many things could go wrong. Left for higher tier Engineers
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/register").permitAll()
                .anyRequest().authenticated()
                );

        return http.build();
    }
}
