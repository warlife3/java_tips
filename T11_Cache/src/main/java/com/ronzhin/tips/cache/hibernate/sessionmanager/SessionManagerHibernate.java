package com.ronzhin.tips.hibernate.hibernate.sessionmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SessionManagerHibernate implements AutoCloseable {

    private final SessionFactory sessionFactory;
    private DatabaseSessionHibernate databaseSession;

    public SessionManagerHibernate(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new RuntimeException("SessionFactory is null");
        }
        this.sessionFactory = sessionFactory;
    }

    public void beginSession() {
        try {
            databaseSession = new DatabaseSessionHibernate(sessionFactory.openSession());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void commitSession() {
        checkSessionAndTransaction();
        try {
            databaseSession.getTransaction().commit();
            databaseSession.getHibernateSession().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void rollbackSession() {
        checkSessionAndTransaction();
        try {
            databaseSession.getTransaction().rollback();
            databaseSession.getHibernateSession().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        if (databaseSession == null) {
            return;
        }
        Session session = databaseSession.getHibernateSession();
        if (session == null || !session.isConnected()) {
            return;
        }

        Transaction transaction = databaseSession.getTransaction();
        if (transaction == null || !transaction.isActive()) {
            return;
        }

        try {
            databaseSession.close();
            databaseSession = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DatabaseSessionHibernate getCurrentSession() {
        checkSessionAndTransaction();
        return databaseSession;
    }

    private void checkSessionAndTransaction() {
        if (databaseSession == null) {
            throw new RuntimeException("DatabaseSession not opened ");
        }
        Session session = databaseSession.getHibernateSession();
        if (session == null || !session.isConnected()) {
            throw new RuntimeException("Session not opened ");
        }

        Transaction transaction = databaseSession.getTransaction();
        if (transaction == null || !transaction.isActive()) {
            throw new RuntimeException("Transaction not opened ");
        }
    }
}
