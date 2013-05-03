/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.controller;

import edent.controller.hibernate.HibernateSessionFactory;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Stepan Tesar
 */
public class HibernateController {
    
    private static Session session = HibernateSessionFactory.getSession();

    /**
     * Ulozi objekt do databaze.
     *
     * @param object objekt ktery se ma ulozit do databaze
     */
    public static void create(Object object) {
        session.flush();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(object);
            tx.commit();
            System.out.println("HBTC: creating "+object.toString()+" (new object)");
        } catch (Exception ex) {
//            ex.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    /**
     * Vymaze objekt z databaze.
     *
     * @param object objekt ktery ma byt smazan z databaze
     */
    public static void delete(Object object) {
        System.out.println("HBTC: deleting "+object.toString());
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(object);
            tx.commit();
        } catch (Exception ex) {
            //ex.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    /**
     * Aktualizuje objekt v databazi.
     *
     * @param object object ktery chceme aktualizovat
     */
    public static void update(Object object) {
        System.out.println("HBTC: updating "+object.toString());
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(object);
            tx.commit();
        } catch (Exception ex) {
//            ex.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
    }

    /**
     * Vykona dotaz, ktery dostane jako parametr a jako vysledek vrati seznam objektu
     * nebo null, pokud se neco pokazi.
     *
     * @param queryText samotny text dotazu
     * @param parameterNames nazvy parametru
     * @param parameterTypes datove typy parametru
     * @param parameterValues hodnoty parametru
     */
    public static List executeQuery(String queryText, String[] parameterNames, String[] parameterTypes, Object[] parameterValues) {
        if (parameterNames.length != parameterValues.length ||
                parameterTypes.length != parameterNames.length) {
            System.out.println("execute params length error: "+parameterNames.length+" : "+parameterValues.length+" : "+parameterTypes.length);
            return null;
        }

        Transaction tx = null;
        Query query;
        
        try {

            tx = session.beginTransaction();
            query = session.createQuery(queryText);

            for (int i = 0; i < parameterNames.length; i++) {
                String parameterType = parameterTypes[i].toLowerCase();
                switch (parameterType) {
                    case "string":
                        query.setString(parameterNames[i], (String) parameterValues[i]);
                        break;
                    case "long":
                        query.setLong(parameterNames[i], (Long) parameterValues[i]);
                        break;
                    case "date":
                        query.setDate(parameterNames[i], (Date) parameterValues[i]);
                        break;
                    case "double":
                        query.setDouble(parameterNames[i], (Double) parameterValues[i]);
                        break;
                    case "boolean":
                        query.setBoolean(parameterNames[i], (Boolean) parameterValues[i]);
                        break;
                }
            }

            List result = query.list();
            
            tx.commit();
            return result;

        } catch (Exception e) {
//            e.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            return null;
        }
    }

    /**
     * Metoda najde datovy zaznam z databaze odpovidajici zadanym parametrum.
     *
     * @param className nazev tridy, pro kterou hledame zaznam
     * @param column nazev promenne, reprezentujici sloupec v databazi
     * @param id hodnota, podle ktere se bude vyhledavat
     *
     * @return odpovidajici databazovy zaznam
     */
    public static Object findById(String className, String column, Long id) {
        String[] paramNames = {"id"};
        String[] paramTypes = {"Long"};
        Object[] paramValues = {id};

        //String idParamName = className.substring(0, 1).toLowerCase() + className.substring(1) + "ID";
        String idParamName = column;

        // POZOR, tady parametrem neni nazev tabulky a sloupecku, ale nazev tridy a jejich atributu
        List res = executeQuery("from " + className + " x where x." + idParamName + " = :id", paramNames, paramTypes, paramValues);

        if (res == null || res.isEmpty()) {
            return null;
        }

        return (Object) res.get(0);
    }

    /**
     * Vrati vsechny zaznamy pro danou tridu.
     *
     * @param className nazev tridy
     * @return List odpovodajicich instanci dane tridy, serazeny podle ID.
     */
    public static List findAll(String className) {
        return findAll(className, "id");
    }
    
    /**
     * Finds all objects of given class, ordered ascendingly by given parameter.
     * @param className
     * @param orderBy
     * @return 
     */
    public static List findAll(String className, String orderBy){
        List res = executeQuery("from " + className + " x order by "+orderBy, new String[0], new String[0], new Object[0]);
        if (res == null || res.isEmpty()) {
            return null;
        }
        return res;
    }
    
    public static List findAllByBoolVal(String className, String orderBy, String column, Boolean value){
        String[] paramNames = {"name"};
        String[] paramTypes = {"Boolean"};
        Object[] paramValues = {value};
        String idParamName = column;
        List res = executeQuery("from " + className + " x where x." + idParamName + " = :name order by "+orderBy, paramNames, paramTypes, paramValues);
        if (res == null || res.isEmpty()) {
            return null;
        }
        return res;
    }

    /**
     * Najde zaznam, jenz odpovida specifikovanym parametrum.
     *
     * @param className nazev tridy, jejiz zaznam hledame
     * @param column nazev promenne (sloupce v databazi) pro ktery hledame odpovidajici hodnotu
     * @param value hodnota, podle ktere se vyhledava odpovidajici zaznam
     * @return
     */
    public static Object findByStringColumnValue(String className, String column, String value) {
        String[] paramNames = {"name"};
        String[] paramTypes = {"String"};
        Object[] paramValues = {value};

        String idParamName = column;

        List res = executeQuery("from " + className + " x where x." + idParamName + " = :name ", paramNames, paramTypes, paramValues);

        if (res == null || res.isEmpty()) {
            return null;
        }

        return (Object) res.get(0);
    }
    
    /**
     * WARNING - to save time, this method only works for filetring patients by
     * fname, sname and birthdate. Editing for general use can be done just by
     * creating a dynamic WHERE x LIKE :param AND clauses, based on given
     * parameters length.
     * Finds a ist of objects of given class matching the given string columns in a
     * pattern string%, that is, the objects with value the specified string value beginning with
     * given value, and also exactly matching the given long value.
     * @param className
     * @param columns the colums to match the values, including the last long column
     * @param stringValues the string values to be matched by LIKE string%
     * @param longValue the long value to be exactly matched. set to -1 to disconsider this value;
     * @param orderBy column name for the ORDER BY command.
     * @return 
     */
    public static List findByStringsAndLong(String className, String[] columns, String[] stringValues, Long longValue, String orderBy) {
//        System.out.println("");
        String[] paramNames = (longValue!=-1)?columns:Arrays.copyOfRange(columns, 0, columns.length-1);
        
        int lenght = (longValue!=-1)?columns.length:columns.length-1;
        String[] paramTypes = new String[lenght];
        for(int i = 0; i < paramTypes.length; i++){
            paramTypes[i] = "String";
        }
        
        if(longValue!=-1){
            paramTypes[columns.length-1] = "Long";
        }
        
        Object[] paramValues = new Object[paramTypes.length];
        for (int i = 0; i < paramValues.length; i++) {
            if(i<stringValues.length) {
                paramValues[i] = stringValues[i]+"%";
            } else {
                paramValues[i] = longValue;
            }
        }
        String bdate = (longValue!=-1)?"and x.birthdate = :bdate":"";
              
        List res = executeQuery("from " + className + " x where x.fname like :fname and x.sname like :sname " + bdate + " order by "+orderBy, paramNames, paramTypes, paramValues);

        return res;
    }

}
