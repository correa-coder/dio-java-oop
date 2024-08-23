package me.dio;

import me.dio.domain.Bootcamp;
import me.dio.domain.Course;
import me.dio.models.Instructor;
import me.dio.models.Student;

import java.util.Set;
import java.util.TreeSet;
import java.util.Optional;

public class App {

    public static void main(String[] args) throws Exception {
        Bootcamp bootcampExample = createBootcampExample();
        enrollStudents(bootcampExample);
        bootcampExample.printInfo();

        Optional<Student> student = bootcampExample.findStudentById(4);
        student.ifPresent((foundStudent) -> {
            bootcampExample.completeCourse(foundStudent, 1);
            bootcampExample.completeCourse(foundStudent, 2);
            bootcampExample.showStudentStatus(foundStudent);
        });

        bootcampExample.showStudentStatus(new Student("Trevor Philips", "trev@eyefind.info"));
    }

    public static Bootcamp createBootcampExample() {
        Instructor david = new Instructor("David", "david@example.com", "backend");
        Instructor ulyssa = new Instructor("Ulyssa", "ulyssa@example.com", "frontend");

        Set<Course> courses = new TreeSet<>();
        courses.add(new Course("Java Basic", 12, david));
        courses.add(new Course("Java Advanced", 6, david));
        courses.add(new Course("PostgreSQL", 4, david));
        courses.add(new Course("Git and GitHub", 2, ulyssa));

        return new Bootcamp("Java Essentials", 2, courses);
    }

    public static void enrollStudents(Bootcamp bootcamp) {
        bootcamp.enrollStudent(new Student("Michael De Santa", "Mike@eyefind.info"));
        bootcamp.enrollStudent(new Student("Franklin Clinton", "Frankie@eyefind.info"));

        // skips because capacity allows only 2 students in this example
        bootcamp.enrollStudent(new Student("Trevor Philips", "trev@eyefind.info"));
    }
}
