package com.ydp.demo;

public class Test {

    @SuppressWarnings("deprecation") //抑制警告
    public static void sing() {
        Person person = new Child();
        person.sing();
    }

    public static String name(){
        Person person = new Child();
       return person.name();
    }

    public static void main(String[] args) {
        sing();
        System.out.println(name());
    }
}
