package upb.safi.AppointmentManagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upb.safi.AppointmentManagement.model.dto.PersonDTO;

/**
 * PersonClient
 *
 * This interface is used to communicate with the person-service through Feign.
 * It provides a way to interact with the person-related functionalities
 * offered by the external microservice by abstracting the HTTP communication.
 *
 * The PersonClient interface is annotated with @FeignClient, which designates
 * it as a Feign client and specifies the name and URL of the service to
 * which it connects.
 *
 * The URL "http://localhost:8081/api/persons" is the base URL for
 * the person microservice, which is expected to expose API endpoints
 * for managing persons.
 *
 * This client currently has one method:
 *
 * 1. getPersonById(Long personId):
 *    - This method is mapped to a GET request at the URL path "/{personId}",
 *      where {personId} is a path variable representing the ID of the person
 *      to be retrieved.
 *    - It returns a PersonDTO object, which is a Data Transfer Object
 *      representing the person's data.
 *    - The method throws an exception if the person with the given ID
 *      cannot be found or if there is an error during the HTTP request.
 *
 * Example usage:
 *
 * PersonClient personClient = ...; // Injected by Spring
 * PersonDTO person = personClient.getPersonById(1L);
 *
 * This will call the person-service and return the person
 * with ID 1 if it exists.
 */
@FeignClient(name = "person-service", url = "http://localhost:8081/api/persons")
public interface PersonClient {

    /**
     * Fetches a person by their ID.
     *
     * @param personId the ID of the person to fetch
     * @return the PersonDTO containing the details of the requested person
     */
    @GetMapping("/{personId}")
    PersonDTO getPersonById(@PathVariable Long personId);
}
