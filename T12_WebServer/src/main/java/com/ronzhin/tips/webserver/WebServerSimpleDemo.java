package com.ronzhin.tips.webserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ronzhin.tips.webserver.db.dao.UserDao;
import com.ronzhin.tips.webserver.db.hibernate.HibernateUtils;
import com.ronzhin.tips.webserver.db.hibernate.dao.UserDaoHibernate;
import com.ronzhin.tips.webserver.db.hibernate.sessionmanager.SessionManagerHibernate;
import com.ronzhin.tips.webserver.db.model.Address;
import com.ronzhin.tips.webserver.db.model.Phone;
import com.ronzhin.tips.webserver.db.model.ROLE;
import com.ronzhin.tips.webserver.db.model.User;
import com.ronzhin.tips.webserver.db.service.DBServiceUser;
import com.ronzhin.tips.webserver.db.service.DbServiceUserCacheImpl;
import com.ronzhin.tips.webserver.web.server.UsersWebServer;
import com.ronzhin.tips.webserver.web.server.UsersWebServerSimple;
import com.ronzhin.tips.webserver.web.service.TemplateProcessor;
import com.ronzhin.tips.webserver.web.service.TemplateProcessorImpl;
import org.hibernate.SessionFactory;

import java.util.Set;

/*
    // Start page
    http://localhost:8080

    // users page
    http://localhost:8080/users

    // REST service
    http://localhost:8080/api/user/3
*/
public class WebServerSimpleDemo {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class, Address.class, Phone.class, ROLE.class);
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserCacheImpl(userDao);

        initiateData(dbServiceUser);

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        UsersWebServer usersWebServer = new UsersWebServerSimple(WEB_SERVER_PORT, dbServiceUser,
                gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }

    private static void initiateData(DBServiceUser dbServiceUser) {
        var roleUser = new ROLE("user");
        var roleAdmin = new ROLE("admin");
        var roleGuest = new ROLE("guest");

        var address1 = new Address("Планерная улица");
        var address2 = new Address("Проспект Авиаконструкторов");
        var address3 = new Address("Комендантский проспект");

        var phone1 = new Phone("+7(123)456-78-90");
        var phone2 = new Phone("+7(987)876-23-23");
        var phone3 = new Phone("+7(111)222-33-44");
        var phone4 = new Phone("+7(098)124-63-74");

        var user1 = new User(0, "vitkus", "Виктория", "12345", address1, Set.of(phone1, phone2), roleAdmin);
        phone1.setUser(user1);
        phone2.setUser(user1);
        var user2 = new User(0, "sevantius", "Всеволод", "11111", address2, Set.of(phone3), roleGuest);
        phone3.setUser(user2);
        var user3 = new User(0, "koshir", "Константин", "24680", address3, Set.of(phone4), roleUser);
        phone4.setUser(user3);

        dbServiceUser.saveUser(user1);
        dbServiceUser.saveUser(user2);
        dbServiceUser.saveUser(user3);
    }
}