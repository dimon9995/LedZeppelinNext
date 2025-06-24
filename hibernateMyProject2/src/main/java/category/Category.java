package category;

import film.Film;
import jakarta.persistence.*;
import lombok.*;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", columnDefinition = "tinyint UNSIGNED not null")
    private Integer id;
    @Column(name = "name", length = 25, nullable = false)
    private String name;
    @Column(name = "last_update")
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FilmCategory> filmCategories = new ArrayList<>();


    public List<FilmCategory> getFilmCategories() {
        List<FilmCategory> filmCategories = new ArrayList<>();
        for (FilmCategory filmCategory : filmCategories) {
            filmCategories.add(filmCategory);
        }
        return filmCategories;
    }

    public Category(String name) {
        this.name = name;
        this.lastUpdated = LocalDateTime.now();
    }



}
