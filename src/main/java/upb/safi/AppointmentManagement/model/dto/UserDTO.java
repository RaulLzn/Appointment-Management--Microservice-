package upb.safi.AppointmentManagement.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * UserDTO
 *
 * This class represents a Data Transfer Object (DTO) for a user associated
 * with the appointment management system. It is used to transfer user-related
 * information, particularly for authentication and authorization purposes.
 *
 * DTOs are helpful for encapsulating data and reducing the amount of
 * information transmitted between the client and server, ensuring only
 * the necessary data is shared.
 */
@Getter
@Setter
public class UserDTO {

    /**
     * The unique identifier for the user.
     */
    private Long userId;

    /**
     * The unique identifier for the person associated with the user.
     */
    private Long personId;

    /**
     * The password for the user account.
     * This should be handled securely and never stored in plaintext.
     */
    private String password;

    /**
     * The authentication token generated for the user.
     * This token is used to verify the user's identity in subsequent requests.
     */
    private String token;
}
