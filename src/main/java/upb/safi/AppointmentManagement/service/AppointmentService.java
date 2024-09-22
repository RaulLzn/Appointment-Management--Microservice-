package upb.safi.AppointmentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upb.safi.AppointmentManagement.client.DependencyClient;
import upb.safi.AppointmentManagement.client.ResponsibleClient;
import upb.safi.AppointmentManagement.client.StudentClient;
import upb.safi.AppointmentManagement.model.dto.AppointmentDTO;
import upb.safi.AppointmentManagement.model.entity.Appointment;
import upb.safi.AppointmentManagement.repository.AppointmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private StudentClient studentClient;

    @Autowired
    private ResponsibleClient responsibleClient;

    @Autowired
    private DependencyClient dependencyClient;

    public Appointment createAppointment(AppointmentDTO appointmentDTO) {
        // Obtenemos las entidades relacionadas mediante Feign
        var responsible = responsibleClient.getResponsibleById(appointmentDTO.getResponsibleId());
        var student = studentClient.getStudentById(appointmentDTO.getStudentId());
        var dependency = dependencyClient.getDependencyById(appointmentDTO.getDependencyId());

        // Creamos la cita
        Appointment appointment = new Appointment();
        appointment.setResponsible(responsible);
        appointment.setStudent(student);
        appointment.setDependency(dependency);
        appointment.setRequestTimestamp(appointmentDTO.getRequestTimestamp());
        appointment.setAppointmentTimestamp(appointmentDTO.getAppointmentTimestamp());
        appointment.setMode(appointmentDTO.getMode());
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setReason(appointmentDTO.getReason());

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
