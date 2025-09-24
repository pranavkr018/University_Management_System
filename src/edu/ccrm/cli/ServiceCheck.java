package edu.ccrm.cli;

import edu.ccrm.domain.*;
import edu.ccrm.service.*;

public class ServiceCheck {
    public static void main(String[] args) {
        // Create services
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();

        // Create and add student
        Student s1 = new Student("S1", "Alice", "alice@uni.edu", "REG001");
        studentService.addStudent(s1);

        // Create and add course
        Course c1 = new Course("CS101", "Intro to CS", 4, Semester.FALL, "CSE");
        courseService.addCourse(c1);

        // Enroll student
        enrollmentService.enroll(s1, c1);

        // Print results
        System.out.println("All Students: " + studentService.getAllStudents());
        System.out.println("All Courses: " + courseService.getAllCourses());
        System.out.println("Enrollments: " + enrollmentService.getEnrollmentsForStudent(s1));
    }
}
