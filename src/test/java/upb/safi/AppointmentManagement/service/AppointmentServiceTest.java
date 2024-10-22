package upb.safi.AppointmentManagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import upb.safi.AppointmentManagement.model.domain.AppointmentMode;
import upb.safi.AppointmentManagement.model.dto.*;
import upb.safi.AppointmentManagement.model.entity.Appointment;
import upb.safi.AppointmentManagement.repository.AppointmentRepository;
import upb.safi.AppointmentManagement.service.impl.AppointmentServiceImpl;
import upb.safi.AppointmentManagement.service.impl.SyncServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private SyncServiceImpl syncService;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAppointment_Success() {
        // Arrange
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setResponsibleId(1L);
        appointmentDTO.setDependencyId(2L);
        appointmentDTO.setStudentId(3L);
        appointmentDTO.setRequestTimestamp(LocalDateTime.now());
        appointmentDTO.setAppointmentTimestamp(LocalDateTime.now().plusDays(1));
        appointmentDTO.setMode(AppointmentMode.PRESENCIAL);
        appointmentDTO.setStatus("PENDING");
        appointmentDTO.setReason("Consulta general");

        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Act
        Appointment result = appointmentService.createAppointment(appointmentDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }

    @Test
    public void testGetAppointment_Success() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        // Act
        Appointment result = appointmentService.getAppointment(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(appointmentRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAppointment_NotFound() {
        // Arrange
        when(appointmentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> appointmentService.getAppointment(1L));
        assertEquals("Cita no encontrada", thrown.getMessage());
        verify(appointmentRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAppointmentDetails_Success() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setStudentid(3L);
        appointment.setResponsibleid(1L);
        appointment.setDependencyid(2L);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(3L);
        studentDTO.setPersonId(5L);

        ResponsibleDTO responsibleDTO = new ResponsibleDTO();
        responsibleDTO.setResponsibleId(1L);

        DependencyDTO dependencyDTO = new DependencyDTO();
        dependencyDTO.setDependencyId(2L);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonId(5L);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(syncService.getStudentById(3L)).thenReturn(studentDTO);
        when(syncService.getResponsibleById(1L)).thenReturn(responsibleDTO);
        when(syncService.getDependencyById(2L)).thenReturn(dependencyDTO);
        when(syncService.getPersonById(5L)).thenReturn(personDTO);

        // Act
        AppointmentDetailsDTO result = appointmentService.getAppointmentDetails(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getAppointment().getId());
        assertEquals(3L, result.getStudent().getStudentId());
        assertEquals(1L, result.getResponsible().getResponsibleId());
        assertEquals(2L, result.getDependency().getDependencyId());
        assertEquals(5L, result.getStudentPerson().getPersonId());
        verify(appointmentRepository, times(1)).findById(1L);
        verify(syncService, times(1)).getStudentById(3L);
        verify(syncService, times(1)).getResponsibleById(1L);
        verify(syncService, times(1)).getDependencyById(2L);
        verify(syncService, times(1)).getPersonById(5L);
    }
}