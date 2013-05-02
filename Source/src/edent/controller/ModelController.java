/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.controller;

import edent.controller.hibernate.DBDAO;
import edent.controller.hibernate.HibernateSessionFactory;
import edent.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
        return this.getAllOf(cls, "id");
    }
    
    private List getAllOf(String cls, String orderBy){
        List list = DBDAO.findAll(cls, orderBy);
        return list!=null?list:new ArrayList();
    }
    
    private List getAllOf(String cls, String orderBy, String where, boolean val){
        List list = DBDAO.findAll(cls, orderBy, where, val);
        return list!=null?list:new ArrayList();
    }
    
    private void delete(String cls, long id){
        Object o = DBDAO.findById(cls, id);
        if(o!=null) {
            DBDAO.delete(o);
        }
    }
    
    private void create(Object o){
        DBDAO.create(o);
    }
    
    private Object getById(String cls, long id){
        return DBDAO.findById(cls, id);
    }
    
    private Object getByString(String cls, String column, String value){
        return DBDAO.findByStringName(cls, column, value);
    }
    
    /*****************************************
     * PUBLIC METHODS
     *****************************************/
    
    /**
     * 
     * @return all ToothState instances from database, or NULL, if none exist.
     */
    public List getToothStates(){
        return this.getAllOf("ToothState");
    }
    
    public List getGumStates(){
        return this.getAllOf("GumState");
    }
    
    public List getUsers(){
        return this.getAllOf("User", "sname");
    }
    
    public List getPatients(){
        return this.getAllOf("Patient", "sname");
    }
    
    public List<Patient> getFilteredPatients(String fname, String sname, long bdate) {
        return DBDAO.findAllByStringsAndOneLong("Patient", new String[]{"fname", "sname", "bdate"}, new String[]{fname, sname}, bdate, "sname");
    }
    
    public List getAppointments(){
        return this.getAllOf("Appointment", "date", "finished", false);
    }
    
    public void deleteToothState(long id){
        this.delete("ToothState", id);
    }
    
    public void deleteGumState(long id){
        this.delete("GumState", id);
    }
    
    public void deleteUser(long id){
        this.delete("User", id);
    }
    
    public void deleteAppointment(long id){
        Appointment appt = (Appointment) this.getById("Appointment", id);
        appt.changeServers(null);
        appt.changePatient(null);
        this.delete("Appointment", id);
    }
    
    //      <editor-fold defaultstate="collapsed" desc="methods for deleting patients - currently disabled">
//    public void deletePatient(long id){
//        Patient p = (Patient) DBDAO.findById("Patient", id);
//        this.deleteMouth(p.getMouth());
//        this.delete("Patient", id);
//    }
//    
//    private void deleteMouth(Mouth m){
//        for(Tooth t : m.getTeeth()){
//            this.deleteTooth(t);
//        }
//        this.deleteHisotry(m.getHistory());
//        this.delete("Mouth", m.getId());
//    }
//    
//    private void deleteTooth(Tooth t){
//        this.delete("Gum", t.getGum().getId());
//        this.delete("Tooth", t.getId());
//    }
//    
//    private void deleteHisotry(History h){
//        for(Diagnosis d : h.getDiagnoses()){
//            this.delete("Diagnosis", d.getId());
//        }
//        this.delete("History", h.getId());
//    }
    //</editor-fold>
    
    public void createToothState(String name, String mark, String icon){
        ToothState ts = new ToothState(name, mark, icon, null);
        this.create(ts);
    }
    
    public void createGumState(String name, String mark, String icon){
        GumState ts = new GumState(name, mark, icon);
        this.create(ts);
    }
    
    public void createUser(String fname, String sname, String uname, String titpre, String titpos, UserType type, String pass){
        User u = new User(fname, sname, uname, titpre, titpos, type, pass);
        this.create(u);
    }
    
    public void createPatient(String fname, String sname, String bnum, long bdate, long created, Set<User> doctors){
        Patient p = new Patient(fname, sname, bnum, bdate, created, doctors);
        this.create(p);
    }
    
    public void createAppointment(long date, String note, long patientId){
        User creator = null; //TODO set logged in user
        Patient patient = this.getPatient(patientId);
        Appointment a = new Appointment(date, note, creator, patient);
        this.create(a);
    }
    
    public void createDiagnosis(long date, User creator, User doctor, String text, History history, Appointment appt){
        Diagnosis d = new Diagnosis(date, creator, doctor, text, history, appt);
        this.create(d);
    }
    
    public ToothState getToothState(long id){
        return (ToothState) this.getById("ToothState", id);
    }
    
    public GumState getGumState(long id){
        return (GumState) this.getById("GumState", id);
    }
    
    public User getUser(long id){
        return (User) this.getById("User", id);
    }
    
    public User getUser(String uname){
        return (User) this.getByString("User", "uname", uname);
    }
    
    public Patient getPatient(long id){
        return (Patient) this.getById("Patient", id);
    }
    
    public Appointment getAppointment(long id){
        return (Appointment) this.getById("Appointment", id);
    }
    
}
