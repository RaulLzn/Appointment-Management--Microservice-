package upb.safi.AppointmentManagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upb.safi.AppointmentManagement.model.dto.DependencyDTO;

@FeignClient(name = "dependency-service", url = "http://localhost:8083")  // Cambiar la URL si es necesario
public interface DependencyClient {

    @GetMapping("/api/dependencies/{id}")
    DependencyDTO getDependencyById(@PathVariable("id") Long dependencyId);
}
