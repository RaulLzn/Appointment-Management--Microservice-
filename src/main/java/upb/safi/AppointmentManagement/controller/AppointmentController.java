package upb.safi.AppointmentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upb.safi.AppointmentManagement.model.domain.AppointmentMode;
import upb.safi.AppointmentManagement.model.dto.AppointmentDetailsDTO;
import upb.safi.AppointmentManagement.model.entity.Appointment;
import upb.safi.AppointmentManagement.model.entity.Dependency;
import upb.safi.AppointmentManagement.model.entity.Responsible;
import upb.safi.AppointmentManagement.model.entity.Student;
import upb.safi.AppointmentManagement.service.AppointmentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Endpoint para listar todas las citas
    @GetMapping
    public ResponseEntity<List<Appointment>> listAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentRequestDTO appointmentDTO) {
        // Convertir los IDs en objetos completos
        Responsible responsible = responsibleService.getResponsibleById(appointmentDTO.getResponsibleId());
        Dependency dependency = dependencyService.getDependencyById(appointmentDTO.getDependencyId());
        Student student = studentService.getStudentById(appointmentDTO.getStudentId());

        // Crear el objeto Appointment
        Appointment appointment = new Appointment();
        appointment.setResponsible(responsible);
        appointment.setDependency(dependency);
        appointment.setStudent(student);
        appointment.setRequestTimestamp(appointmentDTO.getRequestTimestamp());
        appointment.setAppointmentTimestamp(appointmentDTO.getAppointmentTimestamp());
        appointment.setMode(AppointmentMode.valueOf(appointmentDTO.getMode()));  // Asegúrate de que el modo sea válido
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setReason(appointmentDTO.getReason());

        // Guardar la cita en la base de datos
        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
    }


    // Endpoint para obtener una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para modificar una cita (cancelar o actualizar el estado)
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        appointment.setAppointmentId(id);
        Appointment updatedAppointment = appointmentService.updateAppointment(appointment);
        if (updatedAppointment != null) {
            return ResponseEntity.ok(updatedAppointment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint para obtener una cita con detalles completos
    @GetMapping("/details/{id}")
    public ResponseEntity<AppointmentDetailsDTO> getAppointmentDetails(@PathVariable Long id) {
        Optional<AppointmentDetailsDTO> appointmentDetails = appointmentService.getAppointmentDetails(id);
        return appointmentDetails.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
