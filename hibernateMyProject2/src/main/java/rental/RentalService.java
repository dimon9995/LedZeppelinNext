package rental;

import inventory.Inventory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class RentalService {
    private final SessionFactory sessionFactory;

    public RentalService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Rental returnRentalFilm(Integer id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Rental rental = session.get(Rental.class, id);

            if(rental == null){
                throw new IllegalArgumentException("Аренда с id не найдена!" + id);
            }
            if(rental.getReturnDate() != null){
                throw new IllegalStateException("Фильм уже возвращен" + rental.getRentalDate());
            }
            LocalDateTime now = LocalDateTime.now();
            rental.getRentalDate();
            rental.setReturnDate(now);

            Inventory inventory = rental.getInventory();
            if(inventory != null){
                inventory.setLastUpdate(now);
                session.merge(inventory);
            }
            session.merge(rental);
            transaction.commit();
            return rental;
        } catch (Exception e) {
            if (transaction != null && !transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Не удалось вернуть фильм: " + e.getMessage(), e);
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
