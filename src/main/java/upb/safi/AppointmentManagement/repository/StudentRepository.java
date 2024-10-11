package upb.safi.AppointmentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upb.safi.AppointmentManagement.model.entity.Student;

/**
 * StudentRepository
 *
 * This interface serves as a repository for the Student entity.
 * It extends JpaRepository, providing CRUD operations and additional
 * query methods for Student instances.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Additional query methods can be defined here if needed
}
