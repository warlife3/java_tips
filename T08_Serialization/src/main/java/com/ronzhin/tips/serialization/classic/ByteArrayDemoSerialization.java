package com.ronzhin.tips.serialization.classic;

import java.io.*;

public class ByteArrayDemoSerialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        deserialize(serialize());
    }

    private static byte[] serialize() throws IOException {
        var byteArrayOutputStream = new ByteArrayOutputStream();
        var objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        try (byteArrayOutputStream; objectOutputStream) {
            var person = new Person(12, "some name", "value");
            System.out.println("All messages are above were generated while creating object manually");
            System.out.println("P.s. Before Serialization/Deserialization");
            person.newField = "updated field";
            System.out.println("Serializing:  \t" + person);
            objectOutputStream.writeObject(person);
            return byteArrayOutputStream.toByteArray();
        }
    }

    private static void deserialize(byte[] data) throws IOException, ClassNotFoundException {
        var byteArrayInputStream = new ByteArrayInputStream(data);
        try (byteArrayInputStream) {
            var objectInputStream = new ObjectInputStream(byteArrayInputStream);
            var person = (Person) objectInputStream.readObject();
            System.out.println("Deserializing person: \t" + person);
        }
    }
}