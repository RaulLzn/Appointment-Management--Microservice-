package upb.safi.AppointmentManagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upb.safi.AppointmentManagement.model.dto.StudentDTO;

/**
 * StudentClient
 *
 * This interface is used to communicate with the student-service through Feign.
 * It acts as a client to interact with the student microservice, enabling the
 * Appointment Management application to retrieve information regarding students
 * efficiently.
 *
 * The StudentClient interface is annotated with @FeignClient, which indicates
 * that this interface is a Feign client. The service name and the base URL
 * of the student service are defined in the annotation.
 *
 * The URL "http://student-service/api/students" serves as the base endpoint
 * for making requests to the student microservice's API.
 *
 * This client currently exposes one method:
 *
 * 1. getStudentById(Long id):
 *    - This method is mapped to a GET request at the URL path "/{id}",
 *      where {id} is a path variable that represents the ID of the student
 *      to be retrieved.
 *    - It returns a StudentDTO object, which encapsulates the details
 *      of the requested student.
 *    - In case the student with the specified ID cannot be found or if
 *      there are issues during the HTTP request, an exception may be thrown.
 *
 * Example usage:
 *
 * StudentClient studentClient = ...; // Injected by Spring
 * StudentDTO student = studentClient.getStudentById(1L);
 *
 * This will call the student-service and return the student
 * with ID 1 if they exist.
 */
@FeignClient(name = "student-client", url = "http://student-service/api/students")
public interface StudentClient {

    /**
     * Fetches a student by their ID.
     *
     * @param id the ID of the student to fetch
     * @return the StudentDTO containing the details of the requested student
     */
    @GetMapping("/{id}")
    StudentDTO getStudentById(@PathVariable("id") Long id);
}
