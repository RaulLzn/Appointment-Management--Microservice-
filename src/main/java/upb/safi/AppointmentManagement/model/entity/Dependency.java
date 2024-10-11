package upb.safi.AppointmentManagement.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Dependency
 *
 * This class represents the Dependency entity in the appointment management system.
 * It is mapped to the "dependency" table in the database and contains information
 * related to dependencies, including the appointments associated with each dependency.
 */
@Getter
@Setter
@Entity
@Table(name = "dependency")
public class Dependency {

    /**
     * The unique identifier for the dependency.
     * This field is mapped to the "dependencyid" column in the database.
     */
    @Id
    @Column(name = "dependencyid", nullable = false)
    private Long id;

    /**
     * The set of appointments associated with this dependency.
     * This field represents a one-to-many relationship with the Appointment entity.
     */
    @OneToMany(mappedBy = "dependencyid")
    private Set<Appointment> appointments = new LinkedHashSet<>();
}
