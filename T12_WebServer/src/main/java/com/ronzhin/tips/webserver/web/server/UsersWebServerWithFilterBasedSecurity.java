package com.ronzhin.tips.webserver.web.server;

import com.google.gson.Gson;
import com.ronzhin.tips.webserver.db.service.DBServiceUser;
import com.ronzhin.tips.webserver.web.service.TemplateProcessor;
import com.ronzhin.tips.webserver.web.service.UserAuthService;
import com.ronzhin.tips.webserver.web.servlet.AuthorizationFilter;
import com.ronzhin.tips.webserver.web.servlet.LoginServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Arrays;

public class UsersWebServerWithFilterBasedSecurity extends UsersWebServerSimple {
    private final UserAuthService authService;

    public UsersWebServerWithFilterBasedSecurity(int port,
                                                 UserAuthService authService,
                                                 DBServiceUser userService,
                                                 Gson gson,
                                                 TemplateProcessor templateProcessor) {
        super(port, userService, gson, templateProcessor);
        this.authService = authService;
    }

    @Override
    protected Handler applySecurity(ServletContextHandler servletContextHandler, String... paths) {
        servletContextHandler.addServlet(new ServletHolder(new LoginServlet(templateProcessor, authService)), "/login");
        AuthorizationFilter authorizationFilter = new AuthorizationFilter();
        Arrays.stream(paths).forEachOrdered(path -> servletContextHandler.addFilter(new FilterHolder(authorizationFilter), path, null));
        return servletContextHandler;
    }
}
