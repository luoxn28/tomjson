package com.luoxn28.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Person - 测试类
 */
public class Person {

    // ---------------------------------- Instance Variable

    private String name;
    private int age;
    private double money;

    private Dog dog;
    private List<Dog> dogs = new ArrayList<>();

    private Map<Object, Object> map = new HashMap<>();

    // ---------------------------------- Constructors

    public Person() { }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // ---------------------------------- Public Methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                ", dog=" + dog +
                ", dogs=" + dogs +
                '}';
    }

}
