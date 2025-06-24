package country;

import city.City;
import jakarta.persistence.*;
import lombok.*;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country")
@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString(exclude = {"cities"})
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<City> cities = new ArrayList<>();

    @Column(name = "country", length = 50 , nullable = false)
    private String country;


    @Column(name = "last_update")
    private LocalDateTime lastUpdated;
}
