package upb.safi.AppointmentManagement.model.dto;

import lombok.Getter;
import lombok.Setter;
import upb.safi.AppointmentManagement.model.domain.AppointmentMode;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentDTO {
    private Long responsibleId;
    private Long dependencyId;
    private Long studentId;
    private LocalDateTime requestTimestamp;
    private LocalDateTime appointmentTimestamp;
    private AppointmentMode mode;
    private String status;
    private String reason;
}
