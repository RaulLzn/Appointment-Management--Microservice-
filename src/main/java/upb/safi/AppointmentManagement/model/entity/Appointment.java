package upb.safi.AppointmentManagement.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Appointment
 *
 * This class represents the Appointment entity in the appointment management system.
 * It is mapped to the "appointment" table in the database and contains all relevant
 * fields associated with an appointment record, including its relationships with
 * other entities such as responsible, dependency, and student.
 */
@Getter
@Setter
@Entity
@Table(name = "appointment")
public class Appointment {

    /**
     * The unique identifier for the appointment.
     * This field is automatically generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointmentid", nullable = false)
    private Long id;

    /**
     * The unique identifier for the responsible entity associated with this appointment.
     * This is a foreign key that references the responsible entity in the database.
     */
    @JoinColumn(name = "responsibleid")
    private Long responsibleid;

    /**
     * The unique identifier for the dependency entity associated with this appointment.
     * This is a foreign key that references the dependency entity in the database.
     */
    @JoinColumn(name = "dependencyid")
    private Long dependencyid;

    /**
     * The unique identifier for the student entity associated with this appointment.
     * This is a foreign key that references the student entity in the database.
     */
    @JoinColumn(name = "studentid")
    private Long studentid;

    /**
     * The timestamp indicating when the appointment was requested.
     */
    @Column(name = "requesttimestamp")
    private Instant requesttimestamp;

    /**
     * The timestamp indicating when the appointment is scheduled to take place.
     */
    @Column(name = "appointmenttimestamp")
    private Instant appointmenttimestamp;

    /**
     * The mode of the appointment, which can indicate whether it is in-person or virtual.
     * The length of this field is limited to 50 characters.
     */
    @Size(max = 50)
    @Column(name = "mode", length = 50)
    private String mode;

    /**
     * The current status of the appointment (e.g., scheduled, canceled, completed).
     * The length of this field is limited to 50 characters.
     */
    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

    /**
     * The reason for the appointment, providing context for why it has been scheduled.
     * The length of this field is limited to 300 characters.
     */
    @Size(max = 300)
    @Column(name = "reason", length = 300)
    private String reason;

}
