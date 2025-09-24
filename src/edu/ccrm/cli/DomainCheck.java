package edu.ccrm.cli;

import edu.ccrm.domain.*;

public class DomainCheck {
    public static void main(String[] args) {
        Instructor i = new Instructor("I1", "Dr. Smith", "smith@uni.edu", "CSE");
        Course c = new Course("CS101", "Intro to CS", 4, Semester.FALL, "CSE");
        c.setInstructor(i);

        Student s = new Student("S1", "Alice", "alice@uni.edu", "REG001");
        s.enrollCourse(c);

        Enrollment e = new Enrollment(s, c);
        e.assignGrade(Grade.A);

        System.out.println(s.getProfile());
        System.out.println("Enrolled Courses: " + s.getEnrolledCourses());
        System.out.println("Enrollment Record: " + e);
    }
}
