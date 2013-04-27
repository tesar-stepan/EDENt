package edent.controller.hibernate;

import edent.controller.HibernateController;
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
    public static Object findById(String className, String column, Integer id) {
        return HibernateController.findById(className, column, id);
    }

    /**
     * Metoda navraci odpovidajici instanci potomka tridy DBDAO. Vraci pouze
     * nesmazane zaznamy.
     *
     * @param className nazev tridy, pro kterou hledame zaznam
     * @param columnId oznacuje nazev sloupce, kde je uvedeno ID
     * @param id id hodnota, podle ktere se bude vyhledavat
     * @param columnDel nazev sloupce, kde je uvedena hodnota, podle ktere se
     * rozhoduje zda je dany zaznam smazan ci nikoliv
     * @param del ID hodnota, ktera oznacuje nesmazany zaznam
     * @return odpovidajici instance potomka tridy DBDAO
     */
    public static Object findByIdNotDeleted(String className, String columnId, Integer id, String columnDel, Integer del) {
        return HibernateController.findByIdNotDeleted(className, columnId, id, columnDel, del);
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
     * Metoda navraci seznam vsech databazovych zaznamu tridy "className", ktere
     * nejsou smazany (flag "isDeleted" je nastaven na 0).
     *
     * @param className nazev tridy
     * @param columnDeleted nazev sloupce, kde je uvedena hodnota, podle ktere
     * se rozhoduje zda je dany zaznam smazan ci nikoliv
     * @param del ID hodnota, ktera oznacuje nesmazany zaznam
     * @return list odpovodajicich instanci dane tridy
     */
    public static List findAllNotDeleted(String className, String columnDeleted, Integer del) {
        return HibernateController.findAllNotDeleted(className, columnDeleted, del);
    }

    /**
     * Metoda najde zaznam, jenz odpovida specifikovanym parametrum. Vraci pouze
     * nesmazane zaznamy.
     *
     * @param className nazev tridy, jejiz zaznam hledame
     * @param columnName nazev promenne (sloupce v databazi) pro ktery hledame odpovidajici hodnotu
     * @param name hodnota, podle ktere se vyhledava odpovidajici zaznam
     * @param columnDel nazev sloupce, kde je uvedena hodnota, podle ktere se
     * rozhoduje zda je dany zaznam smazan ci nikoliv
     * @param del ID hodnota, ktera oznacuje nesmazany zaznam
     * @return instance daneho potomku tridy DBDAO
     */
    public static Object findByStringNameNotDeleted(String className, String columnName, String name, String columnDel, Integer del) {
        Object o = HibernateController.findByStringNameNotDeleted(className, columnName, name, columnDel, del);
        //System.out.println("in DBDAO: "+o);
        return o;
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
        return HibernateController.findByStringName(className, column, name);
    }
}
