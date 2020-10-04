package com.ronzhin.tips.jdbc.core.model;

import com.ronzhin.tips.jdbc.annotation.Id;
import lombok.Data;

@Data
public class User {

    @Id
    private final long id;
    private final String name;

}