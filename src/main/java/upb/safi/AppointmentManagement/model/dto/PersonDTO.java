package upb.safi.AppointmentManagement.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonDTO {
    private Long personId;
    private String fullName;
    private LocalDate birthDate;
    private String gender;
    private String phone;
    private LocalDate registrationDate;
    private String institutionalEmail;
}
