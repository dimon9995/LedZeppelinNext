package inventory;

import film.Film;
import jakarta.persistence.*;
import lombok.*;
import store.Store;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;


}
