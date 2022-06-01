package eg.gov.iti.jets.persistence.entity.aws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "instance")
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(name = "instance_name")
    private String name;
    @Column(name = "instance_keymaterial")
    private String keyMaterial;
    @Column(name = "instance_publicip")
    private String PublicIp;
    @Column(name = "instance_publicdns")
    private String PublicDNS;
    @Column(name = "instance_password")
    private String decryptedPassword;
    @Column(name = "instance_username")
    private String username;
}