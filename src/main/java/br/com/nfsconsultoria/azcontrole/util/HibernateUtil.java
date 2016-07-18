package br.com.nfsconsultoria.azcontrole.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by luissantos on 06/06/16.
 * @author luissantos
 */
public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = criarFabricaDeSesoes();

    public static SessionFactory getFabricaDeSessoes() {
        return SESSION_FACTORY;
    }

    private static SessionFactory criarFabricaDeSesoes() {
        try {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry registro = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SessionFactory fabrica = configuration.buildSessionFactory(registro);
            return fabrica;
        } catch (Throwable ex) {
            System.err.println("A fabrica de sessões não pode ser criada. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}
