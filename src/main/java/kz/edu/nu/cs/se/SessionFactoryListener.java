package kz.edu.nu.cs.se;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SessionFactoryListener implements ServletContextListener {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            ourSessionFactory = new Configuration().
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        SessionFactory sessionFactory = getSessionFactory();
        if(sessionFactory != null && !sessionFactory.isClosed()){
            sessionFactory.close();
        }
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // static ourSessionFactory is passively initialized
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static SessionFactory getSessionFactory() {
        return ourSessionFactory;
    }
}
