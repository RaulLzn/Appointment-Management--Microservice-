package upb.safi.AppointmentManagement.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import upb.safi.AppointmentManagement.model.dto.*;
import upb.safi.AppointmentManagement.model.entity.Appointment;
import upb.safi.AppointmentManagement.service.AppointmentService;
import upb.safi.AppointmentManagement.service.SyncService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppointmentControllerTests {

    private MockMvc mockMvc;

    @Mock
    private AppointmentService appointmentService;

    @Mock
    private SyncService syncService;

    @InjectMocks
    private AppointmentController appointmentController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateAppointment() throws Exception {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setResponsibleId(1L);
        appointmentDTO.setDependencyId(2L);
        appointmentDTO.setStudentId(3L);
        appointmentDTO.setStatus("Scheduled");
        appointmentDTO.setReason("Consultation");

        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentService.createAppointment(any(AppointmentDTO.class))).thenReturn(appointment);

        mockMvc.perform(post("/api/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testGetAppointment() throws Exception {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentService.getAppointment(eq(1L))).thenReturn(appointment);

        mockMvc.perform(get("/api/appointments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testGetAppointmentDetails() throws Exception {
        AppointmentDetailsDTO appointmentDetailsDTO = new AppointmentDetailsDTO();
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointmentDetailsDTO.setAppointment(appointment);

        when(appointmentService.getAppointmentDetails(eq(1L))).thenReturn(appointmentDetailsDTO);

        mockMvc.perform(get("/api/appointments/details/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.appointment.id").value(1L));
    }

    @Test
    public void testSyncResponsible() throws Exception {
        ResponsibleDTO responsibleDTO = new ResponsibleDTO();
        responsibleDTO.setResponsibleId(1L);

        mockMvc.perform(post("/api/appointments/sync-responsible")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(responsibleDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testSyncStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(1L);

        mockMvc.perform(post("/api/appointments/sync-student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testSyncDependency() throws Exception {
        DependencyDTO dependencyDTO = new DependencyDTO();
        dependencyDTO.setDependencyId(1L);

        mockMvc.perform(post("/api/appointments/sync-dependency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dependencyDTO)))
                .andExpect(status().isOk());
    }
}