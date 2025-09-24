package edu.ccrm.domain;

import java.time.LocalDate;

public class Enrollment {
    private Student student;
    private Course course;
    private LocalDate enrolledDate;
    private Grade grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrolledDate = LocalDate.now();
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public LocalDate getEnrolledDate() { return enrolledDate; }
    public Grade getGrade() { return grade; }

    public void assignGrade(Grade grade) { this.grade = grade; }

    @Override
    public String toString() {
        return student.getFullName() + " enrolled in " + course.getCode() +
               " on " + enrolledDate + (grade != null ? " Grade: " + grade : "");
    }
}
