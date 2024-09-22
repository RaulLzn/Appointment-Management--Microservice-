package upb.safi.AppointmentManagement.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DependencyDTO {
    private Long dependencyId;
    private Long componentId;
    private Long responsibleId;
    private String title;
    private String description;
}
