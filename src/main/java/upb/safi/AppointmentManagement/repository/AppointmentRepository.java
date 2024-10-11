package upb.safi.AppointmentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upb.safi.AppointmentManagement.model.entity.Appointment;

import java.util.List;

/**
 * AppointmentRepository
 *
 * This interface serves as a repository for the Appointment entity.
 * It extends JpaRepository, providing CRUD operations and additional
 * query methods for Appointment instances.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Additional query methods can be defined here if needed

    /**
     * Retrieves a list of appointments associated with the given responsible ID.
     *
     * @param responsibleId the ID of the responsible person to retrieve the appointments
     * @return a list of appointments associated with the given responsible person ID
     */
    List<Appointment> findAllByResponsibleid(Long responsibleId);

    /**
     * Retrieves a list of appointments associated with the given student ID.
     *
     * @param studentId the ID of the student to retrieve the appointments
     * @return a list of appointments associated with the given student ID
     */
    List<Appointment> findAllByStudentid(Long studentId);

}
