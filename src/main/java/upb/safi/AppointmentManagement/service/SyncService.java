package upb.safi.AppointmentManagement.service;

import upb.safi.AppointmentManagement.model.dto.DependencyDTO;
import upb.safi.AppointmentManagement.model.dto.PersonDTO;
import upb.safi.AppointmentManagement.model.dto.ResponsibleDTO;
import upb.safi.AppointmentManagement.model.dto.StudentDTO;

/**
 * SyncService
 *
 * This interface defines the contract for the service layer responsible for synchronizing
 * and retrieving information related to responsible persons, students, dependencies, and persons.
 * It facilitates data consistency across different microservices.
 */
public interface SyncService {

    /**
     * Synchronizes the responsible entity with the provided ResponsibleDTO.
     *
     * @param responsibleDTO the data transfer object containing responsible details
     */
    void syncResponsible(ResponsibleDTO responsibleDTO);

    /**
     * Synchronizes the student entity with the provided StudentDTO.
     *
     * @param studentDTO the data transfer object containing student details
     */
    void syncStudent(StudentDTO studentDTO);

    /**
     * Synchronizes the dependency entity with the provided DependencyDTO.
     *
     * @param dependencyDTO the data transfer object containing dependency details
     */
    void syncDependency(DependencyDTO dependencyDTO);

    /**
     * Retrieves a student by their ID.
     *
     * @param id the ID of the student to retrieve
     * @return the StudentDTO associated with the given ID
     */
    StudentDTO getStudentById(Long id);

    /**
     * Retrieves a responsible person by their ID.
     *
     * @param id the ID of the responsible person to retrieve
     * @return the ResponsibleDTO associated with the given ID
     */
    ResponsibleDTO getResponsibleById(Long id);

    /**
     * Retrieves a dependency by its ID.
     *
     * @param id the ID of the dependency to retrieve
     * @return the DependencyDTO associated with the given ID
     */
    DependencyDTO getDependencyById(Long id);

    /**
     * Retrieves a person by their ID.
     *
     * @param personId the ID of the person to retrieve
     * @return the PersonDTO associated with the given ID
     */
    PersonDTO getPersonById(Long personId);
}
