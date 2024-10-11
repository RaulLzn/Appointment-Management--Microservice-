package upb.safi.AppointmentManagement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upb.safi.AppointmentManagement.model.dto.*;
import upb.safi.AppointmentManagement.model.entity.Appointment;
import upb.safi.AppointmentManagement.repository.AppointmentRepository;
import upb.safi.AppointmentManagement.service.AppointmentService;
import upb.safi.AppointmentManagement.service.SyncService;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    private final AppointmentRepository appointmentRepository;
    private final SyncService syncService;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, SyncService syncService) {
        this.appointmentRepository = appointmentRepository;
        this.syncService = syncService;
    }

    @Transactional
    @Override
    public Appointment createAppointment(AppointmentDTO appointmentDTO) {
        try {
            validateAppointmentDTO(appointmentDTO);
            Appointment appointment = convertToEntity(appointmentDTO);
            return appointmentRepository.save(appointment);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid appointment data: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error creating appointment: {}", e.getMessage(), e);
            throw new RuntimeException("Error creating appointment", e);
        }
    }

    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Appointment not found with id: {}", id);
                    return new RuntimeException("Appointment not found");
                });
    }

    @Override
    public AppointmentDetailsDTO getAppointmentDetails(Long id) {
        Appointment appointment = getAppointment(id);
        StudentDTO student = syncService.getStudentById(appointment.getStudentid());
        ResponsibleDTO responsible = syncService.getResponsibleById(appointment.getResponsibleid());
        DependencyDTO dependency = syncService.getDependencyById(appointment.getDependencyid());
        PersonDTO studentPerson = syncService.getPersonById(student.getPersonId());

        AppointmentDetailsDTO appointmentDetailsDTO = new AppointmentDetailsDTO();
        appointmentDetailsDTO.setAppointment(appointment);
        appointmentDetailsDTO.setStudent(student);
        appointmentDetailsDTO.setResponsible(responsible);
        appointmentDetailsDTO.setDependency(dependency);
        appointmentDetailsDTO.setStudentPerson(studentPerson);

        return appointmentDetailsDTO;
    }

    @Override
    public List<Appointment> getAppointmentsDetailsByResponsibleId(Long id) {
        return appointmentRepository.findAllByResponsibleid(id);
    }

    @Override
    public List<Appointment> getAppointmentsDetailsByStudentId(Long id) {
        return appointmentRepository.findAllByStudentid(id);
    }

    @Override
    public void cancelAppointment(Long id) {
        Appointment appointment = getAppointment(id);
        appointment.setStatus("Cancelled");
        appointmentRepository.save(appointment);
    }

    private void validateAppointmentDTO(AppointmentDTO appointmentDTO) {
        if (appointmentDTO.getResponsibleId() == null ||
                appointmentDTO.getDependencyId() == null ||
                appointmentDTO.getStudentId() == null) {
            throw new IllegalArgumentException("Responsible, dependency, and student IDs cannot be null");
        }
        if (appointmentDTO.getRequestTimestamp() == null ||
                appointmentDTO.getAppointmentTimestamp() == null) {
            throw new IllegalArgumentException("Appointment timestamps cannot be null");
        }
    }

    private Appointment convertToEntity(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        if (appointmentDTO.getRequestTimestamp() != null) {
            appointment.setRequesttimestamp(appointmentDTO.getRequestTimestamp().atZone(ZoneId.of("America/Bogota")).toInstant());
        }
        if (appointmentDTO.getAppointmentTimestamp() != null) {
            appointment.setAppointmenttimestamp(appointmentDTO.getAppointmentTimestamp().atZone(ZoneId.of("America/Bogota")).toInstant());
        }
        appointment.setResponsibleid(appointmentDTO.getResponsibleId());
        appointment.setDependencyid(appointmentDTO.getDependencyId());
        appointment.setStudentid(appointmentDTO.getStudentId());
        appointment.setMode(appointmentDTO.getMode().toString());
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setReason(appointmentDTO.getReason());
        return appointment;
    }
}