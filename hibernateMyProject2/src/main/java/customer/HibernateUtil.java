package customer;

import address.Address;
import category.FilmCategory;
import filmText.FilmText;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import store.Store;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(Customer.class);
                configuration.addAnnotatedClass(Address.class);
                configuration.addAnnotatedClass(FilmText.class);
                configuration.addAnnotatedClass(FilmCategory.class);
                configuration.addAnnotatedClass(Store.class);
                Configuration configure = configuration.configure("hibernate.cfg.xml");
                sessionFactory = configure.buildSessionFactory();
            } catch (Throwable e) {
                System.err.println("Error initial SessionFactory creation failed." + e);
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
