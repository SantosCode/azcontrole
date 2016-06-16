package br.com.nfsconsultoria.azcontrole.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by luissantos on 06/06/16.
 *
 * @author luissantos
 */
public class HibernateContexto implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        HibernateUtil.getFabricaDeSessoes();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HibernateUtil.getFabricaDeSessoes().close();
    }

}
