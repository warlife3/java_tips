package com.ronzhin.tips.webserver.web.servlet;

import com.ronzhin.tips.webserver.db.service.DBServiceUser;
import com.ronzhin.tips.webserver.web.service.TemplateProcessor;
import com.ronzhin.tips.webserver.web.view.UserView;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class UsersServlet extends HttpServlet {

    private static final String USERS_PAGE_TEMPLATE = "users.html";
    private static final String RANDOM_USER_KEY = "randomUser";
    private static final String ALL_USERS_KEY = "users";

    private final DBServiceUser userService;
    private final TemplateProcessor templateProcessor;

    public UsersServlet(TemplateProcessor templateProcessor, DBServiceUser userService) {
        this.templateProcessor = templateProcessor;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        Map<String, Object> paramsMap = new HashMap<>();
        final Optional<UserView> randomUser1 = userService.getRandomUser();
        randomUser1.ifPresent(user -> paramsMap.put(RANDOM_USER_KEY, user));
        paramsMap.put(ALL_USERS_KEY, userService.getAllUsers());

        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(USERS_PAGE_TEMPLATE, paramsMap));
    }

}
