package upb.safi.AppointmentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * AppointmentManagementApplication
 *
 * This class is the entry point of the Appointment Management Spring Boot application.
 * It enables the caching mechanism and the use of Feign clients for making RESTful API calls
 * to other microservices. The application manages appointment-related functionalities
 * and integrates with various services for data synchronization.
 */
@EnableFeignClients // Enables the use of Feign clients to communicate with other microservices
@SpringBootApplication // Marks this class as a Spring Boot application
@EnableCaching // Enables Spring's caching support
public class AppointmentManagementApplication {

	/**
	 * The main method, which serves as the starting point of the Spring Boot application.
	 *
	 * @param args command-line arguments passed during application startup
	 */
	public static void main(String[] args) {
		SpringApplication.run(AppointmentManagementApplication.class, args); // Launches the application
	}
}
