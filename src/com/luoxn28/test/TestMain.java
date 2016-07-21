package com.luoxn28.test;

import com.luoxn28.tomjson.TomJson;

/**
 * TestMain - 测试
 */
public class TestMain {

    public static void main(String[] args) {
        Person person = new Person("luxon28", 23);

        System.out.println(TomJson.toJsonString(person));
    }

}
