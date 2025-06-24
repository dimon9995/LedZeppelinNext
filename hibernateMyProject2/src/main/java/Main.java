import customer.CustomerService;
import customer.HibernateUtil;
import org.hibernate.SessionFactory;
import rental.RentalService;
import task.Task1Create;
import task.Task2Create;
import task.Task3Create;
import task.Task4Create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        try{
            executorService.submit(new Task1Create(sessionFactory, new CustomerService(sessionFactory)));
            Thread.sleep(500);
            executorService.submit(new Task2Create(sessionFactory));
            Thread.sleep(500);
            executorService.submit(new Task3Create(sessionFactory));
            Thread.sleep(500);
            executorService.submit(new Task4Create(sessionFactory));
            Thread.sleep(500);
            executorService.shutdown();
            if(!executorService.isTerminated()) {
                executorService.shutdownNow();
            }
        }catch (InterruptedException e){
            executorService.shutdownNow();
            System.err.println("❌ Выполнение прервано: " + e.getMessage());
        } finally {
            sessionFactory.close();

        }
    }
}