package upb.safi.AppointmentManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upb.safi.AppointmentManagement.model.entity.Appointment;

    public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    }
