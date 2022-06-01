package eg.gov.iti.jets.persistence.entity.aws;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "template_configuration")
public class TemplateConfiguration {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image_ami" ,nullable = false)
    private String amiId;
    @Column(name = "sequrity_croup")
    @ManyToMany
    @JoinTable(name = "template_security_groups" ,joinColumns = @JoinColumn(name = "template_id")
            ,inverseJoinColumns = @JoinColumn(name = "sequrity_group_id")
            ,uniqueConstraints = @UniqueConstraint(columnNames = {"template_id","sequrity_group_id"}))
    private List<SecurityGroup> securityGroups;
    @Column(name = "instance_type" ,nullable = false)
    private String instanceType;
    @Column(name = "instance_keypair")
    private String keyPair;
    @Column(name = "number_of_instance")
    private Integer numberOfInstance=1;
}