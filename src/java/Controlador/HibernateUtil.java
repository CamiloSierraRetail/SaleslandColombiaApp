package Controlador;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ThreadLocalSessionContext;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 **/
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static synchronized void inicializarSesion() {

        System.out.println("session factory:                        " + sessionFactory);
        if ((sessionFactory == null) || (sessionFactory.isClosed() == true)) {
            try {
                // Create the SessionFactory from standard (hibernate.cfg.xml) 
                // config file.
                sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                // Log the exception. 
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionAndUnbindFromThread() {
        Session session = ThreadLocalSessionContext.unbind(sessionFactory);
        if (session != null) {
            session.close();
        }
    }

    public static void closeSessionFactory() {
        if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
            sessionFactory.close();
        }
    }

}
