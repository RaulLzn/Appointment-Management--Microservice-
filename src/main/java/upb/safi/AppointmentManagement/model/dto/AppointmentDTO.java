package upb.safi.AppointmentManagement.model.dto;

import lombok.Getter;
import lombok.Setter;
import upb.safi.AppointmentManagement.model.domain.AppointmentMode;

import java.time.LocalDateTime;

/**
 * AppointmentDTO
 *
 * This class represents a Data Transfer Object (DTO) for an appointment. It is used
 * to transfer appointment-related data between processes, especially when creating
 * or updating appointments in the system.
 *
 * DTOs help encapsulate data and minimize the amount of information sent over the
 * network, making it easier for clients to consume.
 */
@Getter
@Setter
public class AppointmentDTO {

    /**
     * The ID of the responsible party for the appointment.
     */
    private Long responsibleId;

    /**
     * The ID of the dependency associated with the appointment.
     */
    private Long dependencyId;

    /**
     * The ID of the student associated with the appointment.
     */
    private Long studentId;

    /**
     * The timestamp when the appointment was requested.
     */
    private LocalDateTime requestTimestamp;

    /**
     * The timestamp for when the appointment is scheduled to occur.
     */
    private LocalDateTime appointmentTimestamp;

    /**
     * The mode of the appointment, indicating whether it is in-person or virtual.
     */
    private AppointmentMode mode;

    /**
     * The status of the appointment (e.g., scheduled, completed, canceled).
     */
    private String status;

    /**
     * The reason for scheduling the appointment.
     */
    private String reason;
}
