package upb.safi.AppointmentManagement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upb.safi.AppointmentManagement.model.dto.ResponsibleDTO;

@FeignClient(name = "responsible-service", url = "http://localhost:8082")
public interface ResponsibleClient {

    @GetMapping("/api/responsibles/{id}")
    ResponsibleDTO getResponsibleById(@PathVariable("id") Long responsibleId);
}
