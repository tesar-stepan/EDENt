/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.controller;

import edent.controller.hibernate.DBDAO;
import edent.controller.hibernate.HibernateSessionFactory;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Stepan Tesar
 */
public class ModelController {
    private static ModelController instance = new ModelController();
    private static Session session;
    
    public ModelController(){
        HibernateSessionFactory.getSessionFactory();
        session = HibernateSessionFactory.getSession();
        session.beginTransaction();
    }
    
    public static ModelController getInstance(){
        return instance;
    }
    
    public static void shutDown(){
        session.disconnect();
    }
    
    private List getAllOf(String cls){
        List list = DBDAO.findAll(cls);
        return list;
    }
    
    public List getToothStates(){
        return this.getAllOf("ToothState");
    }
    
    
}
