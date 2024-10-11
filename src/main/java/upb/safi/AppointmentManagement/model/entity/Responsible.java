package upb.safi.AppointmentManagement.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Responsible
 *
 * This class represents the Responsible entity in the appointment management system.
 * It is mapped to the "responsible" table in the database and contains information
 * related to responsible individuals, including the appointments associated with each responsible.
 */
@Getter
@Setter
@Entity
@Table(name = "responsible")
public class Responsible {

    /**
     * The unique identifier for the responsible individual.
     * This field is mapped to the "responsibleid" column in the database.
     */
    @Id
    @Column(name = "responsibleid", nullable = false)
    private Long id;

    /**
     * The set of appointments associated with this responsible individual.
     * This field represents a one-to-many relationship with the Appointment entity.
     */
    @OneToMany(mappedBy = "responsibleid")
    private Set<Appointment> appointments = new LinkedHashSet<>();

}
