package com.ronzhin.tips.hibernate;

import com.ronzhin.tips.hibernate.core.dao.UserDao;
import com.ronzhin.tips.hibernate.core.model.Address;
import com.ronzhin.tips.hibernate.core.model.Phone;
import com.ronzhin.tips.hibernate.core.model.User;
import com.ronzhin.tips.hibernate.core.service.DBServiceUser;
import com.ronzhin.tips.hibernate.core.service.DbServiceUserImpl;
import com.ronzhin.tips.hibernate.hibernate.HibernateUtils;
import com.ronzhin.tips.hibernate.hibernate.dao.UserHibernateDao;
import com.ronzhin.tips.hibernate.hibernate.sessionmanager.SessionManagerHibernate;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;

import java.util.Optional;
import java.util.Set;

@Slf4j
public class DbServiceDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class, Address.class, Phone.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserHibernateDao(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserImpl(userDao);


        User user = new User();
        user.setName("User #1");
        Set<Phone> phones = Set.of(
                new Phone("smoke number #1 for user #1"),
                new Phone("smoke number #2 for user #1")
        );

        phones.forEach(phone -> phone.setUser(user));

        Address address = new Address("Smoke address");
        user.setAddress(address);
        user.setPhones(phones);

        long id = dbServiceUser.saveUser(user);
        Optional<User> mayBeCreatedUser = dbServiceUser.getUser(id);

        outputUserOptional("Created user", mayBeCreatedUser);
    }

    private static void outputUserOptional(String header, Optional<User> mayBeUser) {
        System.out.println("-----------------------------------------------------------");
        System.out.println(header);
        mayBeUser.ifPresentOrElse(System.out::println, () -> log.info("User not found"));
    }
}