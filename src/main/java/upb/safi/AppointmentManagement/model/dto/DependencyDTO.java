package upb.safi.AppointmentManagement.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DependencyDTO
 *
 * This class represents a Data Transfer Object (DTO) for a dependency associated
 * with an appointment. It is used to transfer dependency-related data between
 * processes, especially when interacting with services that handle dependencies.
 *
 * DTOs help encapsulate data and minimize the amount of information sent over the
 * network, making it easier for clients to consume.
 */
@Getter
@Setter
public class DependencyDTO {

    /**
     * The unique identifier for the dependency.
     */
    private Long dependencyId;

    /**
     * The ID of the component associated with this dependency.
     */
    private Long componentId;

    /**
     * The ID of the responsible party for this dependency.
     */
    private Long responsibleId;

    /**
     * The title or name of the dependency.
     */
    private String title;

    /**
     * A description providing details about the dependency.
     */
    private String description;
}
