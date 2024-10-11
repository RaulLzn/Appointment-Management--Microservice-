package upb.safi.AppointmentManagement.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import upb.safi.AppointmentManagement.model.dto.*;

import java.time.LocalDate;

/**
 * RedisMockDataLoader
 *
 * This class is responsible for loading mock data into Redis for testing purposes.
 * It initializes various data entities, including students, responsible individuals,
 * dependencies, and person records, and stores them in the Redis cache.
 *
 * The class is marked with the @Component annotation, allowing it to be detected
 * and managed by the Spring container. It uses the RedisTemplate to interact
 * with the Redis datastore, allowing for easy storage and retrieval of data.
 *
 * The mock data is loaded during the application startup through the
 * @PostConstruct lifecycle method, ensuring that the data is available
 * for other components when needed.
 */
@Component
public class RedisMockDataLoader {

    // RedisTemplate is used to interact with Redis
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * This method is called after the bean's properties have been set.
     * It initializes the mock data loading process.
     */
    @PostConstruct
    public void loadMockData() {
        loadStudentData();
        loadResponsibleData();
        loadDependencyData();
        loadPersonData();
    }

    /**
     * Loads mock student data into Redis.
     */
    private void loadStudentData() {
        StudentDTO student = new StudentDTO();
        student.setStudentId(3L);
        student.setPersonId(5L);
        student.setUserId(7L);
        student.setFaculty("Engineering");
        student.setSemester(5);
        student.setSchool("Computer Science");

        // Store the student in Redis with a key "student:3"
        redisTemplate.opsForValue().set("student:3", student);
    }

    /**
     * Loads mock responsible data into Redis.
     */
    private void loadResponsibleData() {
        ResponsibleDTO responsible = new ResponsibleDTO();
        responsible.setResponsibleId(1L);
        responsible.setPersonId(6L);
        responsible.setUserId(8L);
        responsible.setRole("Advisor");

        // Store the responsible in Redis with a key "responsible:1"
        redisTemplate.opsForValue().set("responsible:1", responsible);
    }

    /**
     * Loads mock dependency data into Redis.
     */
    private void loadDependencyData() {
        DependencyDTO dependency = new DependencyDTO();
        dependency.setDependencyId(2L);
        dependency.setComponentId(4L);
        dependency.setResponsibleId(1L);
        dependency.setTitle("Student Counseling");
        dependency.setDescription("Provides guidance for students in various aspects of their academic life.");

        // Store the dependency in Redis with a key "dependency:2"
        redisTemplate.opsForValue().set("dependency:2", dependency);
    }

    /**
     * Loads mock person data into Redis.
     */
    private void loadPersonData() {
        // Creating a mock person for the student
        PersonDTO studentPerson = new PersonDTO();
        studentPerson.setPersonId(5L);
        studentPerson.setFullName("John Doe");
        studentPerson.setBirthDate(LocalDate.of(2000, 5, 15));
        studentPerson.setGender("Male");
        studentPerson.setPhone("+123456789");
        studentPerson.setRegistrationDate(LocalDate.of(2019, 8, 25));
        studentPerson.setInstitutionalEmail("johndoe@university.edu");

        // Store the student person in Redis with a key "person:5"
        redisTemplate.opsForValue().set("person:5", studentPerson);

        // Creating a mock person for the responsible individual
        PersonDTO responsiblePerson = new PersonDTO();
        responsiblePerson.setPersonId(6L);
        responsiblePerson.setFullName("Jane Smith");
        responsiblePerson.setBirthDate(LocalDate.of(1980, 3, 10));
        responsiblePerson.setGender("Female");
        responsiblePerson.setPhone("+987654321");
        responsiblePerson.setRegistrationDate(LocalDate.of(2010, 1, 15));
        responsiblePerson.setInstitutionalEmail("janesmith@university.edu");

        // Store the responsible person in Redis with a key "person:6"
        redisTemplate.opsForValue().set("person:6", responsiblePerson);
    }
}
