///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package edent.testing.model;
//
//import edent.controller.HibernateController;
//import edent.controller.hibernate.HibernateSessionFactory;
//import edent.model.Appointment;
//import edent.model.Diagnosis;
//import edent.model.Gum;
//import edent.model.GumState;
//import edent.model.Mouth;
//import edent.model.Patient;
//import edent.model.Tooth;
//import edent.model.ToothState;
//import edent.model.User;
//import edent.model.UserType;
//import java.util.HashSet;
//import java.util.Set;
//import org.hibernate.Session;
//
///**
// *
// * @author Stepan Tesar
// */
//public class ModelConstructorsTest {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        HibernateSessionFactory.getSessionFactory();
//        Session sf = HibernateSessionFactory.getSession();
//        
//        sf.beginTransaction();
//        
//        System.out.println("database connected");
//        
//        User u = new User("franta", "novak", "drnovak", "doc", "phd", UserType.doctor);
//        System.out.println("create test");
//        
//        System.out.println("select test");
//        HibernateController.create(u);
//        User u = (User) HibernateController.findById("User", "id", (long)2);
//        System.out.println(u);
//        
//        System.out.println("edit test");
//        u.setFname("jes");
//        HibernateController.update(u);
//        
//        System.out.println("persistence test");
//        
//        Patient p = new Patient("franta", "novak", "001.000", 1234, System.currentTimeMillis());
//        System.out.println("create patient");
//        HibernateController.create(p);
//        
//        GumState gs = new GumState("ok", "K", null);
//        System.out.println("create gumstate");
//        HibernateController.create(gs);
//        
//        Mouth m = new Mouth(null,null,p);
//        System.out.println("create mouth");
//        Mouth m = p.getMouth();
//        
//        Gum gum = new Gum(gs, m);
//        
//        System.out.println("create gum");
//        HibernateController.create(gum);
//        
//        ToothState ts = new ToothState("Ok", "K", null, null);
//        System.out.println("create tooth state");
//        HibernateController.create(ts);
//        ToothState ts = (ToothState) HibernateController.findById("ToothState", "id", (long)1);
//        
//        Tooth t = new Tooth(ts, false, true, true, 1, gum, m);
//        System.out.println("create tooth");
//        HibernateController.create(t);
//        Set<Tooth> set3 = new HashSet<>();
//        set3.add(t);
//        Set<Gum> set4 = new HashSet<>();
//        set4.add(gum);
//        m.setTeeth(set3);
//        m.setGums(set4);
//        System.out.println("update mouth");
//        HibernateController.update(m);
//        
//        System.out.println("part 2");
//        
//        Appointment ap = new Appointment(System.currentTimeMillis(), u, p);
//        System.out.println("create apptmnt");
//        HibernateController.create(ap);
//        
//        Diagnosis di = new Diagnosis(System.currentTimeMillis(), u, u, "tralala pneumotorax", null, ap);
//        System.out.println("create diagnosis");
//        HibernateController.create(di);
//        
//        ap.addDiagnosis(di);
//        System.out.println("update apptmtn");
//        System.out.println(ap);
//        HibernateController.update(ap);
//        
//        System.out.println("many-to-many testing");
//        
//        u.addAppointment(ap);
//        u.addPatient(p);
//        HibernateController.update(u);
//        /****/
//        sf.disconnect(); 
//    }
//}
