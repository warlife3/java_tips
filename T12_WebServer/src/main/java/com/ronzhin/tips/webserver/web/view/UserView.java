package com.ronzhin.tips.webserver.web.view;

import com.ronzhin.tips.webserver.db.model.Phone;
import com.ronzhin.tips.webserver.db.model.User;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserView {
    private long id;
    private String name;
    private String login;
    private String password;
    private String address;
    private Set<String> phones;
    private String role;

    public UserView(User user) {
        id = user.getId();
        name = user.getName();
        login = user.getLogin();
        password = user.getPassword();
        address = user.getAddress().getStreet();
        phones = user.getPhones().stream().map(Phone::getNumber).collect(Collectors.toSet());
        role = user.getRole().getName();
    }
}