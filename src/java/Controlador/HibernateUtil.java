/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 **/
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    
    public static void openSessionFactory () {
        if (sessionFactory==null || sessionFactory.isClosed()==true) {
            
            try {
                // Create the SessionFactory from standard (hibernate.cfg.xml) 
                // config file.
                System.out.println("OOOOOOOOOOOOOOOOOOOOOO    ------>    openSessionFactory()  " );
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
    
    // ************************** METODO PARA CERRAR EL SESSION FACTORY  *************************//
    public static void closeSessionFactory() {
        
        System.out.println("EL VALOR DE LA SESSION ES ------------------------------->    " + sessionFactory.isClosed() +"  |||||||||||||||||||||||||   " + sessionFactory);
         if (sessionFactory != null || sessionFactory.isClosed() == false) {
             System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCC  ------>    closeSessionFactory()  " );
             sessionFactory.close();
         }
     }
}
