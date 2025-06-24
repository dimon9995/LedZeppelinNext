package task;

import customer.CustomerService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import rental.Rental;
import rental.RentalService;

import java.util.List;


public class Task2Create implements Runnable {
    private final SessionFactory sessionFactory;
    private final RentalService rentalService;


    public Task2Create(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.rentalService = new RentalService(sessionFactory);

    }



    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        log(threadName, "=".repeat(50));
        log(threadName, "ЗАДАНИЕ 2: Возврат арендованного фильма");
        log(threadName, "=".repeat(50));
        try {
            Rental rental = findActiveRental();

            if (rental == null) {
                log(threadName, "нет активных арендателей");
                return;
            }
            log(threadName, "Найдена аренда ID: " + rental.getId());
            log(threadName, "Customer ID: " + rental.getCustomer().getId());
            log(threadName, "Дата аренды: " + rental.getRentalDate());

            Rental returned = rentalService.returnRentalFilm(rental.getId());
            log(threadName, "успешно!");
        } catch (Exception e) {
            log(threadName, "Ошибка");
            e.printStackTrace();
        }
    }

    private Rental findActiveRental() {
        try (Session session = sessionFactory.openSession()) {
            List<Rental> rentals = session.createQuery(
                            "FROM Rental r WHERE r.returnDate IS NULL", Rental.class)
                    .setMaxResults(1)
                    .list();

            return rentals.isEmpty() ? null : rentals.get(0);
        }
    }

    private void log(String threadName, String massage) {
        System.out.println("[" + threadName + "] " + massage);
    }
}
