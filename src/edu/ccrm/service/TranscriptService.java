package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import java.util.List;

public class TranscriptService {

    public double computeGPA(List<Enrollment> enrollments) {
        int totalCredits = 0;
        int totalPoints = 0;

        for (Enrollment e : enrollments) {
            if (e.getGrade() != null) {
                Course c = e.getCourse();
                int credits = c.getCredits();
                int points = e.getGrade().getPoints();
                totalCredits += credits;
                totalPoints += credits * points;
            }
        }

        return (totalCredits == 0) ? 0.0 : (double) totalPoints / totalCredits;
    }

    public void printTranscript(Student student, List<Enrollment> enrollments) {
        System.out.println("\n===== TRANSCRIPT =====");
        System.out.println("Student: " + student.getFullName() + " (" + student.getRegNo() + ")");
        System.out.println("----------------------");

        for (Enrollment e : enrollments) {
            System.out.printf("%s | %d credits | %s%n",
                    e.getCourse().getCode(),
                    e.getCourse().getCredits(),
                    (e.getGrade() != null ? e.getGrade() : "Not Graded"));
        }

        double gpa = computeGPA(enrollments);
        System.out.printf("GPA: %.2f%n", gpa);
    }
}
