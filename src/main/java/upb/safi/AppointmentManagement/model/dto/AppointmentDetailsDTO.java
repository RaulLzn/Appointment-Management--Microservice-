package upb.safi.AppointmentManagement.model.dto;

import lombok.Getter;
import lombok.Setter;
import upb.safi.AppointmentManagement.model.entity.Appointment;

/**
 * AppointmentDetailsDTO
 *
 * This class represents the Data Transfer Object (DTO) for detailed information
 * about an appointment. It encapsulates the appointment details along with related
 * entities such as the student, responsible party, dependency, and the student's
 * personal information.
 *
 * The use of DTOs helps in transferring data between processes while minimizing
 * the amount of data sent over the network, and it also helps in structuring
 * the data in a way that is easier for the client to consume.
 */
@Getter
@Setter
public class AppointmentDetailsDTO {

    /**
     * The Appointment entity associated with this details DTO.
     */
    private Appointment appointment;

    /**
     * The StudentDTO object representing the student associated with the appointment.
     */
    private StudentDTO student;

    /**
     * The ResponsibleDTO object representing the responsible party associated with the appointment.
     */
    private ResponsibleDTO responsible;

    /**
     * The DependencyDTO object representing the dependency associated with the appointment.
     */
    private DependencyDTO dependency;

    /**
     * The PersonDTO object representing the personal information of the student.
     */
    private PersonDTO studentPerson;
}
