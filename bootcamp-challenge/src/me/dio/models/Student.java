package me.dio.models;

public class Student extends Person {

    public Student(String name, String email) {
        super(name, email);
    }

    @Override
    public String toString() {
        return String.format("%d. %s (%s)", getId(), getName(), getEmail());
    }
}
