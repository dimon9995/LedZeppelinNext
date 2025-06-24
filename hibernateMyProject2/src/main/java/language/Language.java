package language;

import film.Film;
import jakarta.persistence.*;
import lombok.*;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "language")
@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor

public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Integer id;


    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "last_update")
    private Timestamp lastUpdated;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private List<Film> films = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private List<Film> originalLanguageFilms = new ArrayList<>();

}

