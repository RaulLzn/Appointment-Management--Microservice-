package upb.safi.AppointmentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upb.safi.AppointmentManagement.client.DependencyClient;
import upb.safi.AppointmentManagement.model.dto.DependencyDTO;

@Service
public class DependencyService {

    @Autowired
    private DependencyClient dependencyClient;

    public DependencyDTO getDependencyById(Long dependencyId) {
        return dependencyClient.getDependencyById(dependencyId);
    }
}
