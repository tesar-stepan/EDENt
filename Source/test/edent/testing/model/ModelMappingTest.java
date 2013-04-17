/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.testing.model;

/**
import edent.controller.hibernate.HibernateController;
import edent.controller.hibernate.HibernateSessionFactory;
import edent.model.Appointment;
import edent.model.Diagnosis;
import edent.model.Gum;
import edent.model.History;
import edent.model.Mouth;
import edent.model.Patient;
import edent.model.Tooth;
import edent.model.ToothState;
import edent.model.User;
import edent.model.UserType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
**/

/**
 *
 * @author Stepan Tesar
 */
public class ModelMappingTest {

    /**
     * @param args the command line arguments
     * @deprecated 
     */
    public static void main(String[] args) {
        /**
        HibernateSessionFactory.getSessionFactory();
        Session sf = HibernateSessionFactory.getSession();
        
        sf.beginTransaction();
        
        System.out.println("database connected");
        
        User u = new User();
        System.out.println("create test");
        u.setUname("user");
        u.setType(UserType.nurse);
        
        System.out.println("select test");
        HibernateController.create(u);
        u = (User) HibernateController.findById("User", "id", 1);
        System.out.println(u);
        
        System.out.println("edit test");
        u.setFname("jes");
        HibernateController.update(u);
        
        System.out.println("persistence test");
        
        Gum gum = new Gum();
        
        HibernateController.create(gum);
        
        ToothState ts = new ToothState();
        ts.setName("ok");
        ts.setMark("K");
        HibernateController.create(ts);
        
        Tooth t = new Tooth();
        t.setGum(gum);
        t.setState(ts);
        t.setPosition(1);
        HibernateController.create(t);
        
        System.out.println("part 2");
        
        Patient p = new Patient();
        p.setBirthNum("0001/00");
        HibernateController.create(p);
        
        Appointment ap = new Appointment();
        ap.setCreator(u);
        ap.setDate(new Long(123));
        ap.setPatient(p);
        HibernateController.create(ap);
        
        Diagnosis di = new Diagnosis();
        di.setDate(new Long(1234));
        di.setCreator(u);
        di.setOrigAppointment(ap);
        HibernateController.create(di);
        
        List<Diagnosis> list = new ArrayList<>();
        list.add(di);
        ap.setDiagnoses(list);
        HibernateController.update(ap);
        System.out.println(ap);
        
        System.out.println("many-to-many testing");
        
        Set<Appointment> set = new HashSet<>();
        set.add(ap);
        u.setAppointments(set);
        
        Set<Patient> set2 = new HashSet<>();
        set2.add(p);
        u.setPatients(set2);
        HibernateController.update(u);
        
        sf.disconnect();
        
        System.out.println("one-to-one");
        
        History h = new History();
        HibernateController.create(h);
        
        Mouth m = new Mouth();
        m.setOwner(p);
        HibernateController.create(m);
        Set<Tooth> set3 = new HashSet<>();
        set3.add(t);
        m.setTeeth(set3);
        m.setHistory(h);
        System.out.println("owner: "+p.getId());

        HibernateController.update(m);
        t.setMouth(m);
        HibernateController.update(t);
        **/
    }
}
