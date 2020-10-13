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
import com.ronzhin.tips.webserver.web.helpers.FileSystemHelper;
import com.ronzhin.tips.webserver.web.server.UsersWebServer;
import com.ronzhin.tips.webserver.web.server.UsersWebServerWithBasicSecurity;
import com.ronzhin.tips.webserver.web.service.TemplateProcessor;
import com.ronzhin.tips.webserver.web.service.TemplateProcessorImpl;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.hibernate.SessionFactory;

/*
    // Start page
    http://localhost:8080

    // users page
    http://localhost:8080/users

    // REST service
    http://localhost:8080/api/user/3
*/
public class WebServerWithBasicSecurityDemo {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";
    private static final String HASH_LOGIN_SERVICE_CONFIG_NAME = "realm.properties";
    private static final String REALM_NAME = "AnyRealm";

    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class, Address.class, Phone.class);
        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao userDao = new UserDaoHibernate(sessionManager);
        DBServiceUser dbServiceUser = new DbServiceUserCacheImpl(userDao);

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        String hashLoginServiceConfigPath = FileSystemHelper.localFileNameOrResourceNameToFullPath(HASH_LOGIN_SERVICE_CONFIG_NAME);
        LoginService loginService = new HashLoginService(REALM_NAME, hashLoginServiceConfigPath);
        //LoginService loginService = new InMemoryLoginServiceImpl(dbServiceUser);

        UsersWebServer usersWebServer = new UsersWebServerWithBasicSecurity(WEB_SERVER_PORT,
                loginService, dbServiceUser, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
