package task;

import customer.Customer;
import inventory.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import payment.Payment;
import rental.Rental;
import staff.Staff;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
public class Task3Create  implements Runnable {
    private final SessionFactory sessionFactory;

    public Task3Create(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void run() {
        String threadName = Thread.currentThread().getName();
        log(threadName, "=".repeat(50));
        log(threadName, "Задание 3 , аренда фильмов с оплатой ");
        log(threadName, "=".repeat(50));

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
           Inventory inventory =  findInventory(session);
           if(inventory == null){
               log(threadName, "Нет доступных фильмов");
                transaction.rollback();
                return;
           }
           log(threadName, "Фильм найден" + inventory.getId());

            Customer customer = session.get(Customer.class, 1);
            Staff staff = session.get(Staff.class, 1);

            Rental rental = createRental(inventory, customer , staff);
            session.persist(rental);

            Payment payment = createPayment(customer, staff, rental);
            session.persist(payment);
            transaction.commit();
            log(threadName, "✅ УСПЕХ! Транзакция закоммичена");
            log(threadName, "Сумма: " + payment.getAmount() + " руб.");

        } catch (Exception e) {
            if (transaction != null&& !transaction.isActive()) {
                transaction.rollback();
                log(threadName, "⚠️ Транзакция откатилась");
            }
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private Payment createPayment(Customer customer, Staff staff, Rental rental) {
        Payment payment = new Payment();
        payment.setCustomer(customer);
        payment.setStaff(staff);
        payment.setRental(rental);
        payment.setAmount(BigDecimal.valueOf(5));
        payment.setPaymentDate(LocalDateTime.now());
        payment.setLastUpdate(LocalDateTime.now());
        return payment;
    }

    private Rental createRental(Inventory inventory, Customer customer, Staff staff) {
        Rental rental = new Rental();
        rental.setInventory(inventory);
        rental.setCustomer(customer);
        rental.setStaff(staff);
        rental.setRentalDate(LocalDateTime.now());
        rental.setReturnDate(null);
        rental.setLastUpdate(LocalDateTime.now());
        return rental;
    }

    private Inventory findInventory(Session session) {
        Query<Inventory> query = session.createQuery("SELECT i FROM Inventory i WHERE i.id NOT IN " +
                "(SELECT r.inventory.id FROM Rental r WHERE r.returnDate IS NULL)", Inventory.class);
        return query.setMaxResults(1).uniqueResult();
    }


    private void log(String threadName, String massage) {
        System.out.println("[" + threadName + "] " + massage);
    }
}
