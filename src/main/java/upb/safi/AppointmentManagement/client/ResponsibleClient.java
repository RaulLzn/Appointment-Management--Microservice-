package upb.safi.AppointmentManagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upb.safi.AppointmentManagement.model.dto.ResponsibleDTO;

/**
 * ResponsibleClient
 *
 * This interface is used to communicate with the responsible-service through Feign.
 * It provides an abstraction for making HTTP requests to the responsible microservice,
 * allowing the Appointment Management application to easily retrieve information
 * related to responsible individuals.
 *
 * The ResponsibleClient interface is annotated with @FeignClient, which designates
 * it as a Feign client and specifies the name and URL of the service it connects to.
 *
 * The URL "http://responsible-service/api/responsibles" is the base URL for the
 * responsible microservice, which is expected to expose API endpoints
 * for managing responsibles.
 *
 * This client currently has one method:
 *
 * 1. getResponsibleById(Long id):
 *    - This method is mapped to a GET request at the URL path "/{id}",
 *      where {id} is a path variable representing the ID of the responsible
 *      individual to be retrieved.
 *    - It returns a ResponsibleDTO object, which is a Data Transfer Object
 *      representing the details of the responsible person.
 *    - The method may throw an exception if the responsible individual with the
 *      given ID cannot be found or if there is an error during the HTTP request.
 *
 * Example usage:
 *
 * ResponsibleClient responsibleClient = ...; // Injected by Spring
 * ResponsibleDTO responsible = responsibleClient.getResponsibleById(1L);
 *
 * This will call the responsible-service and return the responsible
 * individual with ID 1 if they exist.
 */
@FeignClient(name = "responsible-client", url = "http://responsible-service/api/responsibles")
public interface ResponsibleClient {

    /**
     * Fetches a responsible individual by their ID.
     *
     * @param id the ID of the responsible individual to fetch
     * @return the ResponsibleDTO containing the details of the requested responsible
     */
    @GetMapping("/{id}")
    ResponsibleDTO getResponsibleById(@PathVariable("id") Long id);
}
