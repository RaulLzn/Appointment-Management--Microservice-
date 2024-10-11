package upb.safi.AppointmentManagement.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * StudentDTO
 *
 * This class represents a Data Transfer Object (DTO) for a student
 * associated with an appointment. It is used to transfer data related
 * to student information between processes, particularly when interacting
 * with services that manage student data.
 *
 * DTOs facilitate the encapsulation of data and help minimize the amount of
 * information sent over the network, simplifying client consumption.
 */
@Getter
@Setter
public class StudentDTO {

    /**
     * The unique identifier for the student.
     */
    private Long studentId;

    /**
     * The unique identifier for the person associated with the student.
     */
    private Long personId;

    /**
     * The unique identifier for the user account associated with the student.
     */
    private Long userId;

    /**
     * The faculty to which the student belongs (e.g., Engineering, Science).
     */
    private String faculty;

    /**
     * The semester in which the student is currently enrolled.
     */
    private Integer semester;

    /**
     * The school of the student (e.g., School of Computer Science).
     */
    private String school;
}
