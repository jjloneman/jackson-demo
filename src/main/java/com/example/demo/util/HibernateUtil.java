package com.example.demo.util;

import com.example.demo.models.PostWithList;
import com.example.demo.models.PostWithSet;
import com.example.demo.models.TagWithList;
import com.example.demo.models.TagWithSet;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        Configuration config = new Configuration();
        Properties props = new Properties();

        // SQL properties
        props.put(Environment.DRIVER, "org.h2.Driver");
        props.put(Environment.URL, "jdbc:h2:mem:mydb2;DB_CLOSE_DELAY=-1");
        props.put(Environment.USER, "sa");
        props.put(Environment.PASS, "");
        props.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        props.put(Environment.SHOW_SQL, "true");
        props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        props.put(Environment.HBM2DDL_AUTO, "create-drop");

        config.setProperties(props);

        // JPA entities
        config.addAnnotatedClass(PostWithList.class);
        config.addAnnotatedClass(PostWithSet.class);
        config.addAnnotatedClass(TagWithList.class);
        config.addAnnotatedClass(TagWithSet.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties())
                .build();

        sessionFactory = config.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }
}
