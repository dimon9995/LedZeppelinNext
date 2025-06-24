package address;

import city.City;
import customer.Customer;
import jakarta.persistence.*;
import lombok.*;
import staff.Staff;
import store.Store;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString(exclude = {"city"})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer id;

//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "address_id")
//    private List<Customer> customers = new ArrayList<>();

    @Column(name = "address", length = 50, nullable = false)
    private String addressLine;

    @Column(name = "address2", length = 50, nullable = true)
    private String address2;

    @Column(name = "district", length = 20, nullable = false)
    private String district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "postal_code", length = 10, nullable = false)
    private String postalCode;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
//    private List<Staff> staff ;
//
//    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
//    private List<Store> stores;


}
