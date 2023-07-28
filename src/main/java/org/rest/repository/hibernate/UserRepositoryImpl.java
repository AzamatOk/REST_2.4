package org.rest.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.rest.model.User;
import org.rest.repository.UserRepository;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    SessionFactory sessionFactory = HibernateContext.getSession();
    @Override
    public User getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class,id);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> result = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public User save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public User update(User newUser) {
        User userToUpdate = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        userToUpdate = session.get(User.class, newUser.getId());
        userToUpdate.setEvents(newUser.getEvents());
        userToUpdate.setName(newUser.getName());
        session.update(userToUpdate);
        transaction.commit();

        session.close();

        return userToUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        User userToDelete = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        userToDelete = session.get(User.class, id);
        session.delete(userToDelete);
        transaction.commit();
        session.close();
    }
}
