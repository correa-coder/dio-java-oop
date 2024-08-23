package me.dio.domain;

import java.util.concurrent.atomic.AtomicInteger;

import me.dio.models.Instructor;

public class Course implements Comparable<Course> {
    private static final AtomicInteger count = new AtomicInteger(1);
    private final int id;
    private String title;
    private Instructor instructor;
    private int durationHours;

    public Course(String title, int durationHours, Instructor instructor) {
        this.id = count.getAndIncrement();
        this.title = title;
        this.durationHours = durationHours;
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }

    public void createAssignment() {
    }

    @Override
    public int compareTo(Course other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.format("%d. %s by %s (%dh)", id, title, instructor.getName(), durationHours);
    }
}
