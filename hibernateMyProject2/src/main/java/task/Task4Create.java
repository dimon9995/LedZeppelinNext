package task;

import film.Film;
import film.Rating;
import inventory.Inventory;
import language.Language;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import store.Store;
import java.math.BigDecimal;
import java.time.LocalDateTime;



public class Task4Create implements Runnable {

    private final SessionFactory sessionFactory;

    public Task4Create(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        log(threadName, "=".repeat(50));
        log(threadName, "ЗАДАНИЕ 4: Добавление нового фильма");
        log(threadName, "=".repeat(50));

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Language language = new Language();
            language.setId(1);
            Film film = createFilm(language, threadName);
            session.persist(film);
            log(threadName, "Film создан (ID: " + film.getId() + ")");
            log(threadName, "Название: " + film.getTitle());

            Store store = session.get(Store.class, 1);
            Inventory inventory = createInventory(film, store);
            session.persist(inventory);
            log(threadName, "Inventory создан (ID: " + inventory.getId() + ")");
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                log(threadName, "⚠️ Транзакция откатилась");
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private Inventory createInventory(Film film, Store store) {
        Inventory inventory = new Inventory();
        inventory.setFilm(film);
        inventory.setLastUpdate(LocalDateTime.now());
        inventory.setStore(store);
        return inventory;
    }

    private Film createFilm(Language language, String threadName) {
        Film film = new Film();
        film.setTitle("New Film " + System.currentTimeMillis());  // Уникальное название
        film.setDescription("Auto-generated film from " + threadName);
        film.setReleaseYear(2025);
        film.setTitle("New Film " + System.currentTimeMillis());  // Уникальное название
        film.setDescription("Auto-generated film from " + threadName);
        film.setReleaseYear(2025);
        film.setLength(120);
        film.setLanguage(language);
        film.setOriginalLanguage(language);
        film.setRentalDuration(7);
        film.setRentalRate(BigDecimal.valueOf(4.99));
        film.setReplacementCost(BigDecimal.valueOf(19.99));
        film.setRating("PG");
        film.setSpecialFeatures("Trailers");
        film.setLastUpdate(LocalDateTime.now());
        return film;

    }

    private void log(String threadName, String repeat) {
        System.out.println("[" + threadName + "] " + repeat);
    }

}
