package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {

    private String regNo;
    private boolean active;
    private List<Course> enrolledCourses;

    public Student(String id, String fullName, String email, String regNo) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.active = true;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getRegNo() {
        return regNo;
    }

    public boolean isActive() {
        return active;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void deactivate() {
        this.active = false;
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
    }

    public void unenrollCourse(Course course) {
        enrolledCourses.remove(course);
    }

    @Override
    public String getProfile() {
        return "Student: " + getFullName() + " (" + regNo + ")";
    }

    @Override
    public String toString() {
        return "Student: " + getFullName() + " (" + regNo + ")";
    }

}
