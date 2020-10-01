package com.ronzhin.tips.io.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JacksonDemo {

    private static final String fileName = "T08_IO" + File.separator + "files" + File.separator + "user.json";
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final User user = new User(32, "Jack", "anyData");

    public static void main(String[] args) throws IOException {
        saveUser();
        User loadedUser = loadUser();
        var jsonUser = convertToJson(JacksonDemo.user);

        System.out.println("Original user: " + user);
        System.out.println("Loaded user: " + loadedUser);
        System.out.println("Json user: " + jsonUser);
        User parsedUser = parseJson(jsonUser);
        System.out.println("Parsed user: " + parsedUser);

        var propertyValue = readValue(jsonUser, "user_name");
        System.out.println("propertyValue: " + propertyValue);
    }

    private static void saveUser() throws IOException {
        var file = new File(fileName);
        mapper.writeValue(file, user);
    }

    private static User loadUser() throws IOException {
        var file = new File(fileName);
        return mapper.readValue(file, User.class);
    }

    private static String convertToJson(User user) throws JsonProcessingException {
        return mapper.writeValueAsString(user);
    }

    private static User parseJson(String json) throws JsonProcessingException {
        return mapper.readValue(json, User.class);
    }

    private static String readValue(String json, String serializationName) throws JsonProcessingException {
        JsonNode node = mapper.readTree(json);
        JsonNode jsonProp = node.get(serializationName);
        return jsonProp.textValue();
    }
}
