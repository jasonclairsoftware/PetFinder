package dev.jcclair.PetFinderTest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Password config
 *
 * @author Jason Clair
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * Sets the default password encoder
     * @return - Password Encoder
     */
    @Bean
    public PasswordEncoder setPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
