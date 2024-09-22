package upb.safi.AppointmentManagement.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "person", schema = "gestion_citas")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_gen")
    @SequenceGenerator(name = "person_id_gen", sequenceName = "person_personid_seq", allocationSize = 1)
    @Column(name = "personid", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "personid")
    private Set<Responsible> responsibles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "personid")
    private Set<Student> students = new LinkedHashSet<>();

    @OneToMany(mappedBy = "personid")
    private Set<User> users = new LinkedHashSet<>();

}