package org.rest.repository.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.rest.model.FileModel;
import org.rest.repository.FileRepository;

import java.util.List;

public class FileRepositoryImpl implements FileRepository {
    SessionFactory sessionFactory = HibernateContext.getSession();
    @Override
    public FileModel getById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        FileModel result = session.get(FileModel.class,id);
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public List<FileModel> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<FileModel> result = session.createQuery("FROM FileModel").list();
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public FileModel save(FileModel fileModel) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(fileModel);
        transaction.commit();
        session.close();
        return fileModel;
    }

    @Override
    public FileModel update(FileModel newFileModel) {
        FileModel fileModelToUpdate = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        fileModelToUpdate = session.get(FileModel.class, newFileModel.getId());
        fileModelToUpdate.setFilePath(newFileModel.getFilePath());
        fileModelToUpdate.setName(newFileModel.getName());
        session.update(fileModelToUpdate);
        transaction.commit();

        session.close();

        return fileModelToUpdate;
    }

    @Override
    public void deleteById(Integer id) {
        FileModel fileModelToDelete = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        fileModelToDelete = session.get(FileModel.class, id);
        session.delete(fileModelToDelete);
        transaction.commit();
        session.close();
    }
}
