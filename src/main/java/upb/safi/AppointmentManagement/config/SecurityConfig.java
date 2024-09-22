package upb.safi.AppointmentManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll())  // Permitir todas las solicitudes sin autenticación para pruebas
                .csrf(csrf -> csrf.disable());  // Desactivar CSRF para permitir POST requests en Postman
        return http.build();
    }
}
