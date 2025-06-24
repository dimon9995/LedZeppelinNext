package customer;

import address.Address;
import city.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import store.Store;

import java.time.LocalDateTime;

public class CustomerService {
    private SessionFactory sessionFactory;

    public CustomerService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // new customer
    public Customer createCustomer(Integer storeId, String firstName, String lastName,
                                   String email, Address address, Boolean active) {



        // create customer
         Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            System.out.println("=== Начало транзакции ===");
            if(address != null&& address.getId() == null) {
                System.out.println("Сохраняем новый адрес...");
                if(address.getCity() == null&& address.getCity().getId()!=null) {
                    City city = session.get(City.class, address.getCity().getId());
                    address.setCity(city);
                }
                session.persist(address);
                System.out.println("✅ Адрес сохранён с ID: " + address.getId());
            }
            Store store = session.get(Store.class, storeId);
            if (store == null) {
                throw new IllegalAccessException("Store not found: " + storeId);
            }
            Customer customer = new Customer();
            customer.setStore(store);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setAddress(address);
            customer.setActive(active);
            customer.setCreateDate(LocalDateTime.now());
            customer.setLastUpdate(LocalDateTime.now());
            session.persist(customer);
            System.out.println("✅ Покупатель сохранён с ID: " + customer.getId());
            transaction.commit();
            System.out.println("✅ Транзакция успешно закоммичена!");
            return customer;
        } catch (Exception e) {
            if (transaction != null && !transaction.isActive()) {
                transaction.rollback();
                System.err.println("❌ Транзакция откатилась из-за ошибки!");
            }
            System.err.println("Error in create" + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Ошибка при создании покупателя: " + e.getMessage(), e);
        }finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
