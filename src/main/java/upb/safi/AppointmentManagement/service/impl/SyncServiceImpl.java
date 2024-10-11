package upb.safi.AppointmentManagement.service.impl;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import upb.safi.AppointmentManagement.client.DependencyClient;
import upb.safi.AppointmentManagement.client.PersonClient;
import upb.safi.AppointmentManagement.client.ResponsibleClient;
import upb.safi.AppointmentManagement.client.StudentClient;
import upb.safi.AppointmentManagement.model.dto.DependencyDTO;
import upb.safi.AppointmentManagement.model.dto.PersonDTO;
import upb.safi.AppointmentManagement.model.dto.ResponsibleDTO;
import upb.safi.AppointmentManagement.model.dto.StudentDTO;
import upb.safi.AppointmentManagement.model.entity.Dependency;
import upb.safi.AppointmentManagement.model.entity.Responsible;
import upb.safi.AppointmentManagement.model.entity.Student;
import upb.safi.AppointmentManagement.repository.DependencyRepository;
import upb.safi.AppointmentManagement.repository.ResponsibleRepository;
import upb.safi.AppointmentManagement.repository.StudentRepository;
import upb.safi.AppointmentManagement.service.SyncService;

/**
 * SyncServiceImpl
 *
 * This class implements the SyncService interface, providing methods to synchronize
 * data between local repositories and external microservices. It manages the caching
 * of entities like students, responsibles, dependencies, and persons using Redis,
 * and handles the retrieval of these entities from microservices when not present
 * in the cache.
 */
@Service
public class SyncServiceImpl implements SyncService {

    private static final Logger logger = LoggerFactory.getLogger(SyncServiceImpl.class);

    private final DependencyRepository dependencyRepository;
    private final ResponsibleRepository responsibleRepository;
    private final StudentRepository studentRepository;

    private final StudentClient studentClient;
    private final ResponsibleClient responsibleClient;
    private final DependencyClient dependencyClient;
    private final PersonClient personClient;

    /**
     * Constructs a SyncServiceImpl with the specified repositories and clients.
     *
     * @param dependencyRepository the repository for dependency data
     * @param responsibleRepository the repository for responsible data
     * @param studentRepository the repository for student data
     * @param studentClient the Feign client for student service
     * @param responsibleClient the Feign client for responsible service
     * @param dependencyClient the Feign client for dependency service
     * @param personClient the Feign client for person service
     */
    public SyncServiceImpl(DependencyRepository dependencyRepository, ResponsibleRepository responsibleRepository,
                           StudentRepository studentRepository, StudentClient studentClient,
                           ResponsibleClient responsibleClient, DependencyClient dependencyClient,
                           PersonClient personClient) {
        this.dependencyRepository = dependencyRepository;
        this.responsibleRepository = responsibleRepository;
        this.studentRepository = studentRepository;
        this.studentClient = studentClient;
        this.responsibleClient = responsibleClient;
        this.dependencyClient = dependencyClient;
        this.personClient = personClient;
    }

    /**
     * Synchronizes the responsible entity with the local repository and caches it.
     *
     * @param responsibleDTO the data transfer object containing responsible details
     */
    @CachePut(value = "responsibles", key = "#responsibleDTO.responsibleId")
    @Override
    public void syncResponsible(ResponsibleDTO responsibleDTO) {
        Responsible responsible = new Responsible();
        responsible.setId(responsibleDTO.getResponsibleId());
        responsibleRepository.save(responsible);
    }

    /**
     * Synchronizes the student entity with the local repository and caches it.
     *
     * @param studentDTO the data transfer object containing student details
     */
    @CachePut(value = "students", key = "#studentDTO.studentId")
    @Override
    public void syncStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getStudentId());
        studentRepository.save(student);
    }

    /**
     * Retrieves a person by its ID from the person service, caching the result.
     *
     * @param personId the ID of the person to retrieve
     * @return the PersonDTO associated with the given ID
     */
    @Cacheable(value = "persons", key = "#personId")
    @Override
    public PersonDTO getPersonById(Long personId) {
        return personClient.getPersonById(personId);
    }

    /**
     * Synchronizes the dependency entity with the local repository and caches it.
     *
     * @param dependencyDTO the data transfer object containing dependency details
     */
    @CachePut(value = "dependencies", key = "#dependencyDTO.dependencyId")
    @Override
    public void syncDependency(DependencyDTO dependencyDTO) {
        Dependency dependency = new Dependency();
        dependency.setId(dependencyDTO.getDependencyId());
        dependencyRepository.save(dependency);
    }

    /**
     * Retrieves a student by its ID, attempting to fetch from the cache first,
     * and falling back to the student service if not present in the cache.
     *
     * @param id the ID of the student to retrieve
     * @return the StudentDTO associated with the given ID or null if not found
     */
    @Override
    public StudentDTO getStudentById(Long id) {
        // Attempt to retrieve the student from the cache
        StudentDTO student = getStudentFromCache(id);
        if (student == null) {
            // If not in the cache, query the microservice
            try {
                student = studentClient.getStudentById(id);
                if (student != null) {
                    syncStudent(student); // Sync with local cache
                }
            } catch (FeignException e) {
                logger.error("Error fetching student from microservice: {}", e.getMessage());
                // Optionally throw a custom exception or return a null object
            } catch (Exception e) {
                logger.error("Unexpected error while fetching student from microservice: {}", e.getMessage());
            }
        }
        return student;
    }

    /**
     * Retrieves a responsible entity by its ID, attempting to fetch from the cache first,
     * and falling back to the responsible service if not present in the cache.
     *
     * @param id the ID of the responsible entity to retrieve
     * @return the ResponsibleDTO associated with the given ID or null if not found
     */
    @Override
    public ResponsibleDTO getResponsibleById(Long id) {
        // Attempt to retrieve the responsible from the cache
        ResponsibleDTO responsible = getResponsibleFromCache(id);
        if (responsible == null) {
            // If not in the cache, query the microservice
            try {
                responsible = responsibleClient.getResponsibleById(id);
                if (responsible != null) {
                    syncResponsible(responsible); // Sync with local cache
                }
            } catch (FeignException e) {
                logger.error("Error fetching responsible from microservice: {}", e.getMessage());
            } catch (Exception e) {
                logger.error("Unexpected error while fetching responsible from microservice: {}", e.getMessage());
            }
        }
        return responsible;
    }

    /**
     * Retrieves a dependency by its ID, attempting to fetch from the cache first,
     * and falling back to the dependency service if not present in the cache.
     *
     * @param id the ID of the dependency to retrieve
     * @return the DependencyDTO associated with the given ID or null if not found
     */
    @Override
    public DependencyDTO getDependencyById(Long id) {
        // Attempt to retrieve the dependency from the cache
        DependencyDTO dependency = getDependencyFromCache(id);
        if (dependency == null) {
            // If not in the cache, query the microservice
            try {
                dependency = dependencyClient.getDependencyById(id);
                if (dependency != null) {
                    syncDependency(dependency); // Sync with local cache
                }
            } catch (FeignException e) {
                logger.error("Error fetching dependency from microservice: {}", e.getMessage());
            } catch (Exception e) {
                logger.error("Unexpected error while fetching dependency from microservice: {}", e.getMessage());
            }
        }
        return dependency;
    }

    // Auxiliary methods for retrieving data from the cache

    /**
     * Retrieves a student from the cache.
     * This method is used internally for caching.
     *
     * @param id the ID of the student to retrieve
     * @return null (cache mechanism)
     */
    @Cacheable(value = "students", key = "#id")
    public StudentDTO getStudentFromCache(Long id) {
        return null; // This method is only used for caching
    }

    /**
     * Retrieves a responsible entity from the cache.
     * This method is used internally for caching.
     *
     * @param id the ID of the responsible entity to retrieve
     * @return null (cache mechanism)
     */
    @Cacheable(value = "responsibles", key = "#id")
    public ResponsibleDTO getResponsibleFromCache(Long id) {
        return null; // This method is only used for caching
    }

    /**
     * Retrieves a dependency from the cache.
     * This method is used internally for caching.
     *
     * @param id the ID of the dependency to retrieve
     * @return null (cache mechanism)
     */
    @Cacheable(value = "dependencies", key = "#id")
    public DependencyDTO getDependencyFromCache(Long id) {
        return null; // This method is only used for caching
    }
}
