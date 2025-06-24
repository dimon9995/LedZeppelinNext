package staff;

import address.Address;
import jakarta.persistence.*;
import lombok.*;
import store.Store;
import java.time.LocalDateTime;

@Entity
@Table(name = "staff")
@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    /*@Column(name = "picture")
    private String picture;*/

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "active", nullable = false)
    private String active;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;





}
