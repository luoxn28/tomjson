package com.luoxn28.test;

/**
 * Dog - 测试类
 */
public class Dog {

    // ---------------------------------- Instance Variable

    private String name;

    // ---------------------------------- Constructors

    public Dog() { }

    public Dog(String name) {
        this.name = name;
    }

    // ---------------------------------- Public Methods

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
