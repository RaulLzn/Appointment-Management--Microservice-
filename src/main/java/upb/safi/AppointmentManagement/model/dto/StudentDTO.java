package upb.safi.AppointmentManagement.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private Long studentId;
    private Long personId;
    private Long userId;
    private String faculty;
    private Integer semester;
    private String school;
}
