package film;
import category.Category;
import category.FilmCategory;
import filmText.FilmText;
import jakarta.persistence.*;
import language.Language;
import lombok.*;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "film")

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer id;

   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "film_id")
    private FilmText filmText;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "film")
   private List<FilmCategory> filmCategories = new ArrayList<>();

    public Set<Category> getCategories() {
        Set<Category> categories = new HashSet<>();
        for (FilmCategory fc : filmCategories) {
            categories.add(fc.getCategory());
        }
        return categories;
    }



   @Column(name= "title", length = 128,  nullable = false)
    private String title;
   @Column(name = "description", columnDefinition = "TEXT")
   private String description;

   @Column(name = "release_year")
   private int releaseYear;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "language_id")
   private Language language;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "original_language_id")
   private Language originalLanguage;

   @Column(name = "rental_duration" )
   private Integer rentalDuration;

    @Column(name = "rental_rate", precision = 4, scale = 2)
    private BigDecimal rentalRate;
    @Column(name = "length")
    private Integer length;
    @Column(name = "replacement_cost",  precision = 5, scale = 2)
    private BigDecimal replacementCost;
    @Column(name = "rating")
    private String rating;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;


    @Column(name = "special_features", columnDefinition = "TEXT")
    private String specialFeatures;

}
