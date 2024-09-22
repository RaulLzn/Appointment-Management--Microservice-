package upb.safi.AppointmentManagement.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Class that represents the Responsible entity
 *
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "responsible")
public class Responsible {

    @Id
    @Column(nullable = false)
    private Long responsibleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private Person personid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User userid;

    @OneToMany(mappedBy = "responsible")
    private Set<Appointment> appointments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "responsibleid")
    private Set<Dependency> dependencies = new LinkedHashSet<>();

}
