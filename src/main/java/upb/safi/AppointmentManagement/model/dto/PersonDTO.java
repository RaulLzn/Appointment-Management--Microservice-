package upb.safi.AppointmentManagement.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * PersonDTO
 *
 * This class represents a Data Transfer Object (DTO) for a person associated
 * with an appointment, such as students or responsible parties. It is used to
 * transfer person-related data between processes, particularly when interacting
 * with services that handle personal information.
 *
 * DTOs facilitate the encapsulation of data and help minimize the amount of
 * information sent over the network, simplifying client consumption.
 */
@Getter
@Setter
public class PersonDTO {

    /**
     * The unique identifier for the person.
     */
    private Long personId;

    /**
     * The full name of the person.
     */
    private String fullName;

    /**
     * The birth date of the person.
     */
    private LocalDate birthDate;

    /**
     * The gender of the person (e.g., Male, Female, Non-binary).
     */
    private String gender;

    /**
     * The contact phone number of the person.
     */
    private String phone;

    /**
     * The registration date of the person at the institution.
     */
    private LocalDate registrationDate;

    /**
     * The institutional email address of the person.
     */
    private String institutionalEmail;
}
