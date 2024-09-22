package upb.safi.AppointmentManagement.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import upb.safi.AppointmentManagement.model.dto.AppointmentDetailsDTO;
import upb.safi.AppointmentManagement.service.AppointmentService;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    void testGetAppointmentDetails_success() throws Exception {
        AppointmentDetailsDTO appointmentDetailsDTO = new AppointmentDetailsDTO();
        appointmentDetailsDTO.setAppointmentId(1L);
        appointmentDetailsDTO.setStatus("Agendada");

        when(appointmentService.getAppointmentDetails(anyLong())).thenReturn(Optional.of(appointmentDetailsDTO));

        mockMvc.perform(get("/api/appointments/details/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.appointmentId").value(1L))
                .andExpect(jsonPath("$.status").value("Agendada"));

        verify(appointmentService, times(1)).getAppointmentDetails(anyLong());
    }

    @Test
    void testGetAppointmentDetails_notFound() throws Exception {
        when(appointmentService.getAppointmentDetails(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/appointments/details/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(appointmentService, times(1)).getAppointmentDetails(anyLong());
    }
}
