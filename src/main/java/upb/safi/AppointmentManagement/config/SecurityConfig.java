package upb.safi.AppointmentManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig
 *
 * This class configures security settings for the Appointment Management application.
 * It uses Spring Security to define the security policies for HTTP requests.
 *
 * The configuration allows all requests without authentication and disables CSRF protection,
 * which is suitable for testing purposes, especially when using tools like Postman.
 *
 * The main method is the securityFilterChain, which sets up the security filter chain
 * to manage access control for incoming HTTP requests.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures the security filter chain for the application.
     *
     * @param http the HttpSecurity object to customize the security settings
     * @return the configured SecurityFilterChain
     * @throws Exception if there is a configuration error
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll())  // Allow all requests without authentication for testing purposes
                .csrf(csrf -> csrf.disable());  // Disable CSRF protection to allow POST requests in Postman
        return http.build();
    }
}
