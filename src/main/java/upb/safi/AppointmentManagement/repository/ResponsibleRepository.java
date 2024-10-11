package upb.safi.AppointmentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upb.safi.AppointmentManagement.model.entity.Responsible;

/**
 * ResponsibleRepository
 *
 * This interface serves as a repository for the Responsible entity.
 * It extends JpaRepository, providing CRUD operations and additional
 * query methods for Responsible instances.
 */
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {
    // Additional query methods can be defined here if needed
}
