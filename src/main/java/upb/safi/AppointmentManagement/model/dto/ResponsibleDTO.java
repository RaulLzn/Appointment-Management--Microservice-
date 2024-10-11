package upb.safi.AppointmentManagement.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * ResponsibleDTO
 *
 * This class represents a Data Transfer Object (DTO) for a responsible party
 * associated with an appointment, such as an advisor or counselor. It is used
 * to transfer data related to responsible parties between processes, particularly
 * when interacting with services that manage responsible party information.
 *
 * DTOs facilitate the encapsulation of data and help minimize the amount of
 * information sent over the network, simplifying client consumption.
 */
@Getter
@Setter
public class ResponsibleDTO {

    /**
     * The unique identifier for the responsible party.
     */
    private Long responsibleId;

    /**
     * The unique identifier for the person associated with the responsible party.
     */
    private Long personId;

    /**
     * The unique identifier for the user account associated with the responsible party.
     */
    private Long userId;

    /**
     * The role of the responsible party (e.g., Advisor, Counselor).
     */
    private String role;
}
