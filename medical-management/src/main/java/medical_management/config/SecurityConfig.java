package medical_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())  // Postman ke liye disable
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/patients/**").permitAll() // Patient APIs open
                .anyRequest().authenticated()
            );

        return http.build();
    }
}