package br.com.nfsconsultoria.azcontrole.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author luis
 */
public class HibernateUtil {

    private static final SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

    public static SessionFactory getFabricaDeSessoes() {
        return fabricaDeSessoes;
    }


    /* Converter sessão para conexão para relatório do jasper */
    public static Connection getConexao() {
        Session sessao = fabricaDeSessoes.openSession();

        Connection conexao = sessao.doReturningWork((Connection conn) -> conn);

        return conexao;
    }

    private static SessionFactory criarFabricaDeSessoes() {
        try {
            Configuration configuracao = new Configuration().configure();

            ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();

            SessionFactory fabrica = configuracao.buildSessionFactory(registro);

            return fabrica;
        } catch (Throwable ex) {
            System.err.println("A fábrica de sessões não pode ser criada." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
