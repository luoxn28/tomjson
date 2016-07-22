package com.luoxn28.test;

/**
 * Person - 测试类
 */
public class Person {

    // ---------------------------------- Instance Variable

    private String name;
    private int age;
    private double money;

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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
