package org.rest.repository.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.rest.model.Event;
import org.rest.model.FileModel;
import org.rest.model.User;

public class HibernateContext {
    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure()
                .addAnnotatedClass(Event.class)
                .addAnnotatedClass(FileModel.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
    private HibernateContext(){

    }
    public static SessionFactory getSession(){
        return sessionFactory;
    }
}