package org.rest;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.rest.model.User;
import org.rest.repository.hibernate.HibernateContext;

import java.util.List;
import java.util.Set;

public class RunApp {
    public static void main(String[] args) {
        SessionFactory a = HibernateContext.getSession();
        Session c = a.openSession();
        Transaction b = c.beginTransaction();
        List<User> as = c.createQuery("FROM User").list();
        for(User u : as){
            System.out.println(u.getName());
        }
    }
}