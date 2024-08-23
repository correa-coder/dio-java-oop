package me.dio.models;

public class Instructor extends Person {
    private String specialization;

    public Instructor(String name, String email) {
        super(name, email);
        this.specialization = "N/A";
    }

    public Instructor(String name, String email, String specialization) {
        super(name, email);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
