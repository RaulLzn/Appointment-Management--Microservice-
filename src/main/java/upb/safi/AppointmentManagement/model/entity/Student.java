package upb.safi.AppointmentManagement.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Student
 *
 * This class represents the Student entity in the appointment management system.
 * It is mapped to the "student" table in the database and contains information
 * related to students, including the appointments associated with each student.
 */
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {

    /**
     * The unique identifier for the student.
     * This field is mapped to the "studentid" column in the database.
     */
    @Id
    @Column(name = "studentid", nullable = false)
    private Long id;

    /**
     * The set of appointments associated with this student.
     * This field represents a one-to-many relationship with the Appointment entity.
     */
    @OneToMany(mappedBy = "studentid")
    private Set<Appointment> appointments = new LinkedHashSet<>();

}
