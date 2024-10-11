package upb.safi.AppointmentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upb.safi.AppointmentManagement.model.entity.Dependency;

/**
 * DependencyRepository
 *
 * This interface serves as a repository for the Dependency entity.
 * It extends JpaRepository, providing CRUD operations and additional
 * query methods for Dependency instances.
 */
public interface DependencyRepository extends JpaRepository<Dependency, Long> {
    // Additional query methods can be defined here if needed
}
