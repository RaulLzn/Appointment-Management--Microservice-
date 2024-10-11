package upb.safi.AppointmentManagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upb.safi.AppointmentManagement.model.dto.DependencyDTO;

/**
 * DependencyClient
 *
 * This interface is used to communicate with the dependency-service through Feign.
 * It enables easy access to the dependency-related functionalities provided by
 * the external microservice by abstracting the HTTP communication.
 *
 * The DependencyClient interface is annotated with @FeignClient, which indicates
 * that it is a Feign client and specifies the name and URL of the service
 * to which it connects.
 *
 * The URL "http://dependency-service/api/dependencies" is the base URL for
 * the dependency microservice, and it is expected to provide API endpoints
 * for managing dependencies.
 *
 * This client currently has one method:
 *
 * 1. getDependencyById(Long id):
 *    - This method is mapped to a GET request at the URL path "/{id}",
 *      where {id} is a path variable representing the ID of the dependency
 *      to be retrieved.
 *    - It returns a DependencyDTO object, which is a Data Transfer Object
 *      representing the dependency's data.
 *    - The method throws an exception if the dependency with the given ID
 *      cannot be found or if there is an error during the HTTP request.
 *
 * Example usage:
 *
 * DependencyClient dependencyClient = ...; // Injected by Spring
 * DependencyDTO dependency = dependencyClient.getDependencyById(1L);
 *
 * This will call the dependency-service and return the dependency
 * with ID 1 if it exists.
 */
@FeignClient(name = "dependency-client", url = "http://dependency-service/api/dependencies")
public interface DependencyClient {

    /**
     * Fetches a dependency by its ID.
     *
     * @param id the ID of the dependency to fetch
     * @return the DependencyDTO containing the details of the requested dependency
     */
    @GetMapping("/{id}")
    DependencyDTO getDependencyById(@PathVariable("id") Long id);
}
