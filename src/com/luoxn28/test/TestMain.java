package com.luoxn28.test;

import com.luoxn28.tomjson.TomJson;
import com.luoxn28.tomjson.deserializer.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * TestMain - 测试
 */
public class TestMain {

    public static void main(String[] args) {
        Person person = new Person("luxon28", 23);
        person.setMoney(13.14);
        person.setDog(new Dog("gay"));
        person.setDogs(new ArrayList<Dog>(Arrays.asList(new Dog("gay1"), new Dog("gar2"))));

        System.out.println("----- Object序列化为json -----");
        String jsonString = TomJson.toJsonString(person);
        System.out.println(jsonString);

        System.out.println("----- json序列化为Object，未提供Object类型 -----");
        JsonObject jsonObject = TomJson.parseObject(jsonString);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue() + " | ");
        }
        System.out.println();

        System.out.println("----- json序列化为Object，提供Object类型 -----");
        Person jsonPerson = TomJson.parseObject(jsonString, Person.class);
        System.out.println(jsonPerson);
    }

}
