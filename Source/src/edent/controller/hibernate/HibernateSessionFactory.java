package edent.controller.hibernate;

import java.io.File;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate session factory
 *
 * @author Jarda
 */
public class HibernateSessionFactory {

    /**
     * Location of hibernate.cfg.xml file. Location should be on the classpath
     * as Hibernate uses #resourceAsStream style lookup for its configuration
     * file. The default classpath location of the hibernate config file is in
     * the default package. Use #setConfigFile() to update the location of the
     * configuration file for the current session.
     */
    private static final String CONFIG_FILE_LOCATION = "config/hibernate.cfg.xml";
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<>();
    private static Configuration configuration = new Configuration();
    private static org.hibernate.SessionFactory sessionFactory = null;
    private static String configFile = CONFIG_FILE_LOCATION;


    /**
     * Returns the ThreadLocal Session instance. Lazy initialize the
     * <code>SessionFactory</code> if needed.
     *
     * @return Session
     * @throws HibernateException
     */
    public static Session getSession() throws HibernateException {
        Session session = (Session) threadLocal.get();

        if (session == null || !session.isOpen()) {
            if (sessionFactory == null) {
                rebuildSessionFactory();
            }
            session = (sessionFactory != null) ? sessionFactory.openSession()
                    : null;
            threadLocal.set(session);
        }
        return session;
    }

    /**
     * Rebuild hibernate session factory
     *
     */
    public static void rebuildSessionFactory() throws HibernateException {

        configuration.configure(configFile);
        sessionFactory = configuration.buildSessionFactory();

    }

    /**
     * Close the single hibernate session instance.
     *
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }

    /**
     * return session factory
     *
     */
    public static org.hibernate.SessionFactory getSessionFactory() throws HibernateException {
        if (sessionFactory == null) {
//            try{
            //configParser = new ConfigParser("config/config.xml");
            //configuration.configure(configFile);
            File hibernateConfigFile = new File(CONFIG_FILE_LOCATION);
            if (!hibernateConfigFile.exists()) {
                System.err.println("Konfiguracni soubor " + hibernateConfigFile.getPath() + " " + hibernateConfigFile.getAbsolutePath() + " nebyl nalezen");
                //System.exit(-1);
            }
            //sessionFactory = configuration.buildSessionFactory();
            sessionFactory = new Configuration().configure(hibernateConfigFile).buildSessionFactory();
//            }catch(HibernateException e){
//                throw e;
//            }
        }
        return sessionFactory;
    }

    /**
     * return session factory
     *
     * session factory will be rebuilded in the next call
     */
    public static void setConfigFile(String configFile) {
        HibernateSessionFactory.configFile = configFile;
        sessionFactory = null;
    }

    /**
     * return hibernate configuration
     *
     */
    public static Configuration getConfiguration() {
        return configuration;
    }
}
