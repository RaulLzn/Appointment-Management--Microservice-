package upb.safi.AppointmentManagement.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"user\"", schema = "gestion_citas")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_gen")
    @SequenceGenerator(name = "user_id_gen", sequenceName = "user_userid_seq", allocationSize = 1)
    @Column(name = "userid", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personid")
    private Person personid;

    @OneToMany(mappedBy = "userid")
    private Set<Responsible> responsibles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userid")
    private Set<Student> students = new LinkedHashSet<>();

}