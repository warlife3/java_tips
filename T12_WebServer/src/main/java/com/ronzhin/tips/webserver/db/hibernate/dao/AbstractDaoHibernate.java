package com.ronzhin.tips.webserver.db.hibernate.dao;

import com.ronzhin.tips.webserver.db.dao.Dao;
import com.ronzhin.tips.webserver.db.hibernate.sessionmanager.DatabaseSessionHibernate;
import com.ronzhin.tips.webserver.db.hibernate.sessionmanager.SessionManagerHibernate;
import com.ronzhin.tips.webserver.db.model.IDEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractDaoHibernate<V, T extends IDEntity<V>> implements Dao<V, T> {

    private final SessionManagerHibernate sessionManager;

    @Getter
    private final Class<T> clazz;

    @Override
    public Optional<T> findById(V id) {
        Session hibernateSession = getSession();
        return Optional.ofNullable(hibernateSession.find(clazz, id));
    }

    @Override
    public List<T> findAll() {
        Session hibernateSession = getSession();
        return hibernateSession.createQuery("FROM User", clazz).list();
    }

    protected Session getSession() {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        return currentSession.getHibernateSession();
    }

    @Override
    public V insert(T object) {
        Session session = getSession();
        session.save(object);
        return object.getId();
    }

    @Override
    public void update(T object) {
        Session session = getSession();
        session.update(object);
    }

    @Override
    public SessionManagerHibernate getSessionManager() {
        return sessionManager;
    }

}