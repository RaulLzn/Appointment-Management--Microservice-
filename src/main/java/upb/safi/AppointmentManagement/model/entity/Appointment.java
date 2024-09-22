package upb.safi.AppointmentManagement.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import upb.safi.AppointmentManagement.model.domain.AppointmentMode;
import java.time.LocalDateTime;

/**
 * Class that represents the Appointment entity
 *
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "responsibleId", nullable = false)
    private Responsible responsible;

    @ManyToOne
    @JoinColumn(name = "dependencyId", nullable = false)
    private Dependency dependency;

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private Student student;

    private LocalDateTime requestTimestamp;
    private LocalDateTime appointmentTimestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private AppointmentMode mode;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(length = 300)
    private String reason;

}