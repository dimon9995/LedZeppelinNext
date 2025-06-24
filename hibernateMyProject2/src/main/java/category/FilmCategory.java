package category;

import film.Film;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "film_category")
@Getter
@Setter
@Data
public class FilmCategory {
    @EmbeddedId
    private FilmCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    @MapsId("film")
    private Film film;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @Column(name = "last_update")
    private LocalDateTime lastUpdated;

    public FilmCategory(Film film, Category category) {
        this.film = film;
        this.category = category;
        this.id = new FilmCategoryId(film.getId(), category.getId());
        this.lastUpdated = LocalDateTime.now();
    }


}

