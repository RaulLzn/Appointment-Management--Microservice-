package upb.safi.AppointmentManagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upb.safi.AppointmentManagement.model.dto.StudentDTO;

@FeignClient(name = "student-service", url = "http://localhost:8081")  // Cambiar la URL si es necesario
public interface StudentClient {

    @GetMapping("/api/students/{id}")
    StudentDTO getStudentById(@PathVariable("id") Long studentId);
}
