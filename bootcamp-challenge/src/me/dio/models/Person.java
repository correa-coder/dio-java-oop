package me.dio.models;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Person implements Comparable<Person> {
    private static final AtomicInteger count = new AtomicInteger(1);
    private final int id;

    protected String name;
    protected String email;

    public Person(String name, String email) {
        this.id = count.getAndIncrement();
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.id, other.id);
    }
}
