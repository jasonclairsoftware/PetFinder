package dev.jcclair.PetFinderBack.security;

import jakarta.ws.rs.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

/**
 * Security configurations for Spring. This is used to be overridden to allow CORS to operate accordingly.
 *
 * @author Jason Clair
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource;
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    /**
     * Overloaded CTOR used to handle traffic security
     *
     * @param corsConfigurationSource - CORS security authentication
     */
    public SecurityConfig(
            CorsConfigurationSource corsConfigurationSource,
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        this.corsConfigurationSource = corsConfigurationSource;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
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
                .requestMatchers(HttpMethod.OPTIONS, "/**", "/users/register/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/users/login").permitAll().anyRequest().authenticated()
                )
                .formLogin(loginForm ->
                        loginForm
                                .loginProcessingUrl("/users/login")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/index")
                                .permitAll()
                );

        return http.build();
    }

    /*
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }


}
