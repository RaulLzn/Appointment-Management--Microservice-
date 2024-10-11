package upb.safi.AppointmentManagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import upb.safi.AppointmentManagement.model.dto.*;
import upb.safi.AppointmentManagement.model.entity.Appointment;
import upb.safi.AppointmentManagement.repository.AppointmentRepository;
import upb.safi.AppointmentManagement.service.impl.AppointmentServiceImpl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private SyncService syncService;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAppointmentDetails_Success() {
        // Arrange
        // Crear un Appointment de ejemplo
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setStudentid(3L);
        appointment.setResponsibleid(1L);
        appointment.setDependencyid(2L);
        appointment.setRequesttimestamp(Instant.now());
        appointment.setAppointmenttimestamp(Instant.now().plusSeconds(3600));

        // Crear DTOs simulados
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(3L);
        studentDTO.setPersonId(5L);

        ResponsibleDTO responsibleDTO = new ResponsibleDTO();
        responsibleDTO.setResponsibleId(1L);

        DependencyDTO dependencyDTO = new DependencyDTO();
        dependencyDTO.setDependencyId(2L);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setPersonId(5L);
        personDTO.setFullName("John Doe");

        // Configurar las respuestas de los mocks
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
        assertEquals("John Doe", result.getStudentPerson().getFullName());

        // Verificar que los métodos fueron llamados la cantidad esperada de veces
        verify(appointmentRepository, times(1)).findById(1L);
        verify(syncService, times(1)).getStudentById(3L);
        verify(syncService, times(1)).getResponsibleById(1L);
        verify(syncService, times(1)).getDependencyById(2L);
        verify(syncService, times(1)).getPersonById(5L);
    }

    @Test
    public void testGetAppointmentDetails_NotFound() {
        // Arrange
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            appointmentService.getAppointmentDetails(1L);
        });

        assertEquals("Cita no encontrada", thrown.getMessage());
        verify(appointmentRepository, times(1)).findById(1L);
    }
}
