package com.example.oving3alt;

public class Person {
    private String name;
    private String birthday;

    public Person(String line1, String line2) {
        name = line1;
        birthday = "Bursdag: " + line2;
    }

    public void changeName(String text) {
        name = text;
    }

    public void changeBirthday(String text) {
        birthday = "Bursdag: " + text;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }
}
