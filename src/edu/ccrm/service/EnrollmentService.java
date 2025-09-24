package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public void enroll(Student student, Course course) {
        Enrollment e = new Enrollment(student, course);
        enrollments.add(e);
        student.enrollCourse(course); // Keep student's list in sync
    }

    public List<Enrollment> getEnrollmentsForStudent(Student student) {
        return enrollments.stream()
                .filter(e -> e.getStudent().equals(student))
                .collect(Collectors.toList());
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollments;
    }
}
