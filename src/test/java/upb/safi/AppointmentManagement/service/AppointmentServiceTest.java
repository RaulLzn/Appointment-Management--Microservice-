package upb.safi.AppointmentManagement.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import upb.safi.AppointmentManagement.client.DependencyClient;
import upb.safi.AppointmentManagement.client.ResponsibleClient;
import upb.safi.AppointmentManagement.client.StudentClient;
import upb.safi.AppointmentManagement.model.domain.AppointmentMode;
import upb.safi.AppointmentManagement.model.dto.*;
import upb.safi.AppointmentManagement.model.entity.Appointment;
import upb.safi.AppointmentManagement.model.entity.Dependency;
import upb.safi.AppointmentManagement.model.entity.Responsible;
import upb.safi.AppointmentManagement.model.entity.Student;
import upb.safi.AppointmentManagement.repository.AppointmentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private StudentClient studentClient;

    @Mock
    private ResponsibleClient responsibleClient;

    @Mock
    private DependencyClient dependencyClient;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAppointmentDetails_success() {
        // Configuramos una cita simulada
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1L);
        appointment.setMode(AppointmentMode.PRESENCIAL);
        appointment.setStatus("Agendada");

        // Initialize and set the Student entity
        Student studentEntity = new Student();
        studentEntity.setStudentId(123L); // Set the ID or any necessary fields
        appointment.setStudent(studentEntity);

        // Initialize and set the Responsible entity if needed
        Responsible responsibleEntity = new Responsible();
        responsibleEntity.setResponsibleId(456L); // Set the ID or any necessary fields
        appointment.setResponsible(responsibleEntity);

        // Initialize and set the Dependency entity if needed
        Dependency dependencyEntity = new Dependency();
        dependencyEntity.setDependencyId(789L); // Set the ID or any necessary fields
        appointment.setDependency(dependencyEntity);

        // Datos simulados para Feign Clients
        StudentDTO studentDTO = new StudentDTO();
        ResponsibleDTO responsibleDTO = new ResponsibleDTO();
        DependencyDTO dependencyDTO = new DependencyDTO();

        // Simulamos la interacción con el repositorio y los clientes Feign
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));
        when(studentClient.getStudentById(anyLong())).thenReturn(studentDTO);
        when(responsibleClient.getResponsibleById(anyLong())).thenReturn(responsibleDTO);
        when(dependencyClient.getDependencyById(anyLong())).thenReturn(dependencyDTO);

        // Llamamos al método a probar
        Optional<AppointmentDetailsDTO> result = appointmentService.getAppointmentDetails(1L);

        // Verificamos que los resultados sean correctos
        assertTrue(result.isPresent());
        assertEquals("Agendada", result.get().getStatus());
        assertNotNull(result.get().getStudent());
        assertNotNull(result.get().getResponsible());
        assertNotNull(result.get().getDependency());

        // Verificamos que los métodos hayan sido llamados
        verify(appointmentRepository, times(1)).findById(anyLong());
        verify(studentClient, times(1)).getStudentById(anyLong());
        verify(responsibleClient, times(1)).getResponsibleById(anyLong());
        verify(dependencyClient, times(1)).getDependencyById(anyLong());
    }

    @Test
    void testGetAppointmentDetails_notFound() {
        // Simulamos que no se encuentra la cita
        when(appointmentRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Llamamos al método a probar
        Optional<AppointmentDetailsDTO> result = appointmentService.getAppointmentDetails(1L);

        // Verificamos que no se encontró ninguna cita
        assertFalse(result.isPresent());

        // Verificamos que no se hayan hecho llamadas a los Feign clients
        verify(studentClient, times(0)).getStudentById(anyLong());
        verify(responsibleClient, times(0)).getResponsibleById(anyLong());
        verify(dependencyClient, times(0)).getDependencyById(anyLong());
    }
}
