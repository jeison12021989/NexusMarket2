package application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for the API.
 *
 * Authentication and role-based authorization (RG-01 and RG-03 of the
 * functional specification) are out of the scope of this delivery, so
 * every endpoint is left open and CSRF protection is disabled, which is
 * what a stateless REST API consumed by Postman or a script needs.
 * A future iteration would replace permitAll() with roles
 * (Administrator, Seller, Buyer, Logistics Operator, Supervisor).
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}
