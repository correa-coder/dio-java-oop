package me.dio.domain;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import me.dio.models.Student;

public class Bootcamp {

    private String name;
    private Set<Course> courses;
    private Set<Student> enrolledStudents = new TreeSet<>();
    private Map<Integer, Set<Course>> completedCourses = new HashMap<>(); // <student id, course>
    private int maxCapacity;

    public Bootcamp(String name, int maxCapacity, Set<Course> courses) {
        this.name = name;
        this.maxCapacity = maxCapacity;

        if (courses == null || courses.isEmpty()) {
            throw new IllegalArgumentException("Bootcamp should have at least one course");
        }

        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int calculateTotalDurationHours() {
        return courses.stream()
                .mapToInt(Course::getDurationHours)
                .sum();
    }

    public boolean enrollStudent(Student student) {
        if (enrolledStudents.size() < maxCapacity) {
            enrolledStudents.add(student);
            completedCourses.put(student.getId(), new TreeSet<>());
            return true;
        }

        return false;
    }

    public void printInfo() {
        System.out.println(String.format("%s (%dh of content)", name, calculateTotalDurationHours()));
        System.out.println(String.format("Total students: %d", enrolledStudents.size()));
        System.out.println(String.format("%nCourses (%d):", courses.size()));
        courses.stream().forEach((course) -> System.out.println("- " + course.toString()));

        if (!enrolledStudents.isEmpty()) {
            System.out.println("\nStudents enrolled:");
            enrolledStudents.stream().forEach((student) -> System.out.println("- " + student.toString()));
        }
    }

    public Optional<Student> findStudentById(int id) {
        return enrolledStudents.stream().filter(student -> student.getId() == id).findFirst();
    }

    public void completeCourse(Student student, int courseId) {
        Optional<Course> course = courses.stream()
                .filter(currentCourse -> currentCourse.getId() == courseId)
                .findFirst();
        course.ifPresent((foundCourse) -> {
            completedCourses.get(student.getId()).add(foundCourse);
        });
    }

    public void showStudentStatus(Student student) {
        Optional<Student> foundStudent = enrolledStudents.stream()
                .filter((enrolledStudent) -> student.getId() == enrolledStudent.getId())
                .findFirst();

        foundStudent.ifPresentOrElse(
                currentStudent -> {
                    System.out.println(String.format("%n%s status:", currentStudent.getName()));

                    int totalCompleted = completedCourses.get(currentStudent.getId()).size();

                    System.out.println(
                            String.format("- %d courses completed", totalCompleted));
                    System.out.println();
                },
                () -> System.out.println(String.format("%s is not enrolled in %s", student.getName(), this.name)));
    }
}
