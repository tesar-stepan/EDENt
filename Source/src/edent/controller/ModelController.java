/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.controller;

import edent.controller.hibernate.HibernateSessionFactory;
import org.hibernate.Session;

/**
 *
 * @author Stepan Tesar
 */
public class ModelController {
    private static ModelController instance = new ModelController();
    private static Session session = HibernateSessionFactory.getSession();
    
    public ModelController(){
//        HibernateSessionFactory.getSessionFactory();
//        Session sf = ;
        session.beginTransaction();
    }
    
    public static ModelController getInstance(){
        return instance;
    }
    
    public static void shutDown(){
        session.disconnect();
    }
    
    
}
