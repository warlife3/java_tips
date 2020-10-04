package com.ronzhin.tips.jdbc;

import com.ronzhin.tips.jdbc.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForTest {

    @Id
    private long id;
    private String name;

}