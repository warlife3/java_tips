package com.ronzhin.tips.webserver.db.hibernate.dao;

import com.ronzhin.tips.webserver.db.dao.UserDao;
import com.ronzhin.tips.webserver.db.hibernate.sessionmanager.SessionManagerHibernate;
import com.ronzhin.tips.webserver.db.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class UserDaoHibernate extends AbstractDaoHibernate<Long, User> implements UserDao {

    public UserDaoHibernate(SessionManagerHibernate sessionManager) {
        super(sessionManager, User.class);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        Session session = getSession();
        Query<User> namedQuery = session.createNamedQuery(User.QUERY_FIND_BY_LOGIN, getClazz()).setParameter("login", login);
        User user = namedQuery.getSingleResult();
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findByRole(String role) {
        Session session = getSession();
        Query<User> namedQuery = session.createNamedQuery(User.QUERY_FIND_BY_ROLE, getClazz()).setParameter("role", role);
        return namedQuery.getResultList();
    }

    @Override
    public void insertOrUpdate(User user) {
        if (user.getId() > 0)
            update(user);
        else insert(user);
    }
}