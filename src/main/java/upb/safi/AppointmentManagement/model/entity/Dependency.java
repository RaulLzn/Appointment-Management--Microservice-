package upb.safi.AppointmentManagement.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Class that represents the Dependency entity
 *
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "dependency")
public class Dependency {

    @Id
    @Column(nullable = false)
    private Long dependencyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsibleid")
    private Responsible responsibleid;

    @OneToMany(mappedBy = "dependency")
    private Set<Appointment> appointments = new LinkedHashSet<>();

}
