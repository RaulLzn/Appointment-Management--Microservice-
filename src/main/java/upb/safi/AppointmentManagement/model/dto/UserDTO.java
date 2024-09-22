package upb.safi.AppointmentManagement.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private Long personId;
    private String password;
    private String token;
}
