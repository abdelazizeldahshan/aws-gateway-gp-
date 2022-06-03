package eg.gov.iti.jets.persistence.entity;

import eg.gov.iti.jets.persistence.entity.aws.Instance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    //todo can join multiple tracks or not
    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;


    @OneToMany(mappedBy = "creator")
    private List<Instance> createdInstances = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_granted_instances",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "instance_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "instance_id"}))
    private List<Instance> grantedInstances = new ArrayList<>();

}