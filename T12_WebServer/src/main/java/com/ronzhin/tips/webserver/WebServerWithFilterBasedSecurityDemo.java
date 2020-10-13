package com.ronzhin.tips.webserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ronzhin.tips.webserver.db.dao.UserDao;
import com.ronzhin.tips.webserver.db.hibernate.HibernateUtils;
import com.ronzhin.tips.webserver.db.hibernate.dao.UserDaoHibernate;
import com.ronzhin.tips.webserver.db.hibernate.sessionmanager.SessionManagerHibernate;
import com.ronzhin.tips.webserver.db.model.Address;
import com.ronzhin.tips.webserver.db.model.Phone;
import com.ronzhin.tips.webserver.db.model.User;
import com.ronzhin.tips.webserver.db.service.DBServiceUser;
import com.ronzhin.tips.webserver.db.service.DbServiceUserCacheImpl;
import com.ronzhin.tips.webserver.web.server.UsersWebServer;
import com.ronzhin.tips.webserver.web.server.UsersWebServerWithFilterBasedSecurity;
import com.ronzhin.tips.webserver.web.service.TemplateProcessor;
import com.ronzhin.tips.webserver.web.service.TemplateProcessorImpl;
import com.ronzhin.tips.webserver.web.service.UserAuthService;
import com.ronzhin.tips.webserver.web.service.UserAuthServiceImpl;
import org.hibernate.SessionFactory;


/*
    // Start page
    http://localhost:8080

    // users page
    http://localhost:8080/users

    // REST service
    http://localhost:8080/api/user/3
*/
public class WebServerWithFilterBasedSecurityDemo {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class, Address.class, Phone.class);
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserCacheImpl(userDao);


        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(dbServiceUser);

        UsersWebServer usersWebServer = new UsersWebServerWithFilterBasedSecurity(WEB_SERVER_PORT,
                authService, dbServiceUser, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
