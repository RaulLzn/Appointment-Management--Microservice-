package upb.safi.AppointmentManagement.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsibleDTO {
    private Long responsibleId;
    private Long personId;
    private Long userId;
    private String role;
}
