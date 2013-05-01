/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.testing.model;

import edent.controller.HibernateController;
import edent.controller.hibernate.HibernateSessionFactory;
import edent.model.ToothState;
import org.hibernate.Session;

/**
 *
 * @author Stepan Tesar
 */
public class ModelCreatingTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HibernateSessionFactory.getSessionFactory();
        Session sf = HibernateSessionFactory.getSession();

        sf.beginTransaction();

        System.out.println("database connected");

        System.out.println("select all");
        HibernateController.findAll("ToothState");
        
        System.out.println("select");
        ToothState ts2 = (ToothState) HibernateController.findById("ToothState", "id", (long) 1);
        System.out.println(ts2+" "+ts2.getId());
        
        
        ToothState ts = new ToothState("Ok", "K", null, null);
        System.out.println("create tooth state");
        HibernateController.create(ts);
        System.out.println(ts+" "+ts.getId());

    }
}