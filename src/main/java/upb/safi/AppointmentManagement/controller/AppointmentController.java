package upb.safi.AppointmentManagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upb.safi.AppointmentManagement.model.dto.AppointmentDTO;
import upb.safi.AppointmentManagement.model.dto.AppointmentDetailsDTO;
import upb.safi.AppointmentManagement.model.dto.DependencyDTO;
import upb.safi.AppointmentManagement.model.dto.ResponsibleDTO;
import upb.safi.AppointmentManagement.model.dto.StudentDTO;
import upb.safi.AppointmentManagement.model.entity.Appointment;
import upb.safi.AppointmentManagement.service.AppointmentService;
import upb.safi.AppointmentManagement.service.SyncService;

import java.util.List;

/**
 * AppointmentController
 *
 * This controller handles HTTP requests related to appointments in the Appointment Management application.
 * It provides endpoints for creating, retrieving, and synchronizing appointment-related entities.
 */
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final SyncService syncService;

    /**
     * Constructs an AppointmentController with the specified services.
     *
     * @param appointmentService the service to manage appointment-related operations
     * @param syncService        the service to synchronize related entities
     */
    public AppointmentController(AppointmentService appointmentService, SyncService syncService) {
        this.appointmentService = appointmentService;
        this.syncService = syncService;
    }

    /**
     * Endpoint to create a new appointment.
     *
     * @param appointmentDTO the appointment data transfer object containing appointment details
     * @return a ResponseEntity containing the created appointment or an error response
     */
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointmentDTO));
    }

    /**
     * Endpoint to retrieve an appointment by its ID.
     *
     * @param appointmentId the ID of the appointment to retrieve
     * @return a ResponseEntity containing the appointment details or an error response
     */
    @GetMapping("/{appointmentId}")
    public ResponseEntity<?> getAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(appointmentService.getAppointment(appointmentId));
    }

    /**
     * Endpoint to retrieve the complete details of an appointment by its ID.
     *
     * @param appointmentId the ID of the appointment for which details are requested
     * @return a ResponseEntity containing the complete appointment details or an error response
     */
    @GetMapping("/details/{appointmentId}")
    public ResponseEntity<AppointmentDetailsDTO> getAppointmentDetails(@PathVariable Long appointmentId) {
        AppointmentDetailsDTO appointmentDetails = appointmentService.getAppointmentDetails(appointmentId);
        return ResponseEntity.ok(appointmentDetails);
    }

    /**
     * Endpoint to retrieve all appointments by responsible ID.
     *
     * @param responsibleId the ID of the responsible person to retrieve appointments for
     * @return a ResponseEntity containing the list of appointments or an error response
     */
    @GetMapping("/responsible/{responsibleId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByResponsibleId(@PathVariable Long responsibleId) {
        List<Appointment> appointments = appointmentService.getAppointmentsDetailsByResponsibleId(responsibleId);
        return ResponseEntity.ok(appointments);
    }

    /**
     * Endpoint to retrieve all appointments by student ID.
     *
     * @param studentId the ID of the student to retrieve appointments for
     * @return a ResponseEntity containing the list of appointments or an error response
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByStudentId(@PathVariable Long studentId) {
        List<Appointment> appointments = appointmentService.getAppointmentsDetailsByStudentId(studentId);
        return ResponseEntity.ok(appointments);
    }

    /**
     * Endpoint to cancel an appointment by its ID.
     *
     * @param appointmentId the ID of the appointment to cancel
     * @return a ResponseEntity indicating the status of the cancellation
     */
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.ok().build();
    }

    // Endpoints for synchronizing related entities

    /**
     * Endpoint to synchronize a responsible entity.
     *
     * @param responsibleDTO the responsible data transfer object to synchronize
     * @return a ResponseEntity indicating the synchronization status
     */
    @PostMapping("/sync-responsible")
    public ResponseEntity<Void> syncResponsible(@RequestBody ResponsibleDTO responsibleDTO) {
        syncService.syncResponsible(responsibleDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to synchronize a student entity.
     *
     * @param studentDTO the student data transfer object to synchronize
     * @return a ResponseEntity indicating the synchronization status
     */
    @PostMapping("/sync-student")
    public ResponseEntity<Void> syncStudent(@RequestBody StudentDTO studentDTO) {
        syncService.syncStudent(studentDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to synchronize a dependency entity.
     *
     * @param dependencyDTO the dependency data transfer object to synchronize
     * @return a ResponseEntity indicating the synchronization status
     */
    @PostMapping("/sync-dependency")
    public ResponseEntity<Void> syncDependency(@RequestBody DependencyDTO dependencyDTO) {
        syncService.syncDependency(dependencyDTO);
        return ResponseEntity.ok().build();
    }
}
