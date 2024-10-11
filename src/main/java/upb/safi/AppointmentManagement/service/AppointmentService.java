package upb.safi.AppointmentManagement.service;

import upb.safi.AppointmentManagement.model.dto.AppointmentDTO;
import upb.safi.AppointmentManagement.model.dto.AppointmentDetailsDTO;
import upb.safi.AppointmentManagement.model.entity.Appointment;

import java.util.List;

/**
 * AppointmentService
 *
 * This interface defines the contract for the service layer responsible for managing appointments.
 * It provides methods for creating, retrieving, and retrieving detailed information about appointments.
 */
public interface AppointmentService {

    /**
     * Creates a new appointment based on the provided AppointmentDTO.
     *
     * @param appointmentDTO the data transfer object containing appointment details
     * @return the created Appointment entity
     */
    Appointment createAppointment(AppointmentDTO appointmentDTO);

    /**
     * Retrieves an appointment by its ID.
     *
     * @param id the ID of the appointment to retrieve
     * @return the Appointment entity associated with the given ID
     */
    Appointment getAppointment(Long id);

    /**
     * Retrieves detailed information about an appointment by its ID.
     *
     * @param id the ID of the appointment to retrieve
     * @return an AppointmentDetailsDTO containing detailed information about the appointment
     */
    AppointmentDetailsDTO getAppointmentDetails(Long id);

    /**
     * Retrieves a list of Appointment entities associated with the given responsible ID.
     *
     * @param id the ID of the responsible person to retrieve the appointments
     * @return a list of Appointment entities associated with the given responsible person ID
     */
    List<Appointment> getAppointmentsDetailsByResponsibleId(Long id);


    /**
     * Retrieves a list of Appointment entities associated with the given student ID.
     *
     * @param id the ID of the student to retrieve the appointments
     * @return a list of Appointment entities associated with the given student ID
     */
    List<Appointment> getAppointmentsDetailsByStudentId(Long id);

    /**
     * Cancels an appointment by its ID.
     *
     * @param id the ID of the appointment to cancel
     */
    void cancelAppointment(Long id);
}
