package edent.controller.hibernate;

import edent.controller.HibernateController;
import edent.model.Patient;
import java.io.Serializable;
import java.util.List;

/**
 * Trida zajistujici komunikace domenovych trid s databazi. Konkretni domenove
 * tridy dedi od teto tridy.
 *
 * @author Jarda
 */
public class DBDAO implements Serializable {
    
    /**
     * Ulozi objekt do databaze.
     *
     * @param object objekt ktery se ma ulozit do databaze
     */
    public static void create(Object object) {
        HibernateController.create(object);
    }

    /**
     * Vymaze objekt z databaze.
     *
     * @param object objekt ktery ma byt smazan z databaze
     */
    public static void delete(Object object) {
        HibernateController.delete(object);
    }

    /**
     * Aktualizuje objekt v databazi.
     *
     * @param object object ktery chceme aktualizovat
     */
    public static void update(Object object) {
        HibernateController.update(object);
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
        return HibernateController.executeQuery(queryText, parameterNames, parameterTypes, parameterValues);
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
    public static Object findById(String className, Long id) {
        return HibernateController.findById(className, "id", id);
    }

    /**
     * Vrati vsechny zaznamy pro danou tridu.
     *
     * @param className nazev tridy
     * @return List odpovodajicich instanci dane tridy.
     */
    public static List findAll(String className) {
        List l = HibernateController.findAll(className);
        return l;
    }
    
    /**
     * Vrati vsechny zaznay pro danou tridu, serazene podle orderBy
     * @param className
     * @param orderBy
     * @return 
     */
    public static List findAll(String className, String orderBy) {
        List l = HibernateController.findAll(className, orderBy);
        return l;
    }
    
    public static List findAll(String className, String orderBy, String where, boolean b) {
        List l = HibernateController.findAllByBoolVal(className, orderBy, where, b);
        return l;
    }

    /**
     * Najde zaznam, jenz odpovida specifikovanym parametrum.
     *
     * @param className nazev tridy, jejiz zaznam hledame
     * @param column nazev promenne (sloupce v databazi) pro ktery hledame odpovidajici hodnotu
     * @param name hodnota, podle ktere se vyhledava odpovidajici zaznam
     * @return
     */
    public static Object findByStringName(String className, String column, String name) {
        return HibernateController.findByStringColumnValue(className, column, name);
    }
    
    /**
     * WARNING - to save time, this method only works for filetring patients by
     * fname, sname and birthdate.
     * @see HibernateController.findByStringsAndLong
     * @param className
     * @param columns
     * @param stringValues
     * @param longValue
     * @param orderBy
     * @return 
     */
    public static List<Patient> findAllByStringsAndOneLong(String className, String[] columns, String[] stringValues, long longValue, String orderBy) {
        return HibernateController.findByStringsAndLong(className, columns, stringValues, longValue, orderBy);
    }
}
