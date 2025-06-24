package task;

import address.Address;
import city.City;
import customer.Customer;
import customer.CustomerService;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;

public class Task1Create implements Runnable {
    private final SessionFactory sessionFactory;
    private final CustomerService customerService;

    public Task1Create(SessionFactory sessionFactory, CustomerService customerService) {
        this.sessionFactory = sessionFactory;
        this.customerService = customerService;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        log(threadName, "=".repeat(50));
        log(threadName, "ЗАДАНИЕ 1: Создание нового покупателя");
        log(threadName, "=".repeat(50));
        try {
            City city = createCity();
            Address address = createAddress(city);
            Customer customer = customerService.createCustomer(
                    1, "Покупатель",
                    "Фамилия", "dm222",
                    address, true);


            log(threadName, "✅ УСПЕХ!");
            log(threadName, "Customer ID: " + customer.getId());
            log(threadName, "Имя: " + customer.getFirstName() + " " + customer.getLastName());
            log(threadName, "Email: " + customer.getEmail());

        } catch (Exception e) {
            log(threadName, "Ошибка: " + e.getMessage());
        }
    }

    private Address createAddress(City city) {
        Address address = new Address();
        address.setAddressLine("Lenina Street, " + (System.currentTimeMillis() % 100));
        address.setAddress2("Apt " + (int)(Math.random() * 100));
        address.setDistrict("Central");
        address.setCity(city);
        address.setPostalCode("424000");
        address.setPhone("+7900" + (int)(Math.random() * 10000000));
        address.setLastUpdate(LocalDateTime.now());
        return address;
    }

    private City createCity() {
        City city = new City();
        city.setId(1);
        return city;
    }


    private void log(String threadName, String message) {
        System.out.println("[Thread " + threadName + "] " + message);
    }
}

