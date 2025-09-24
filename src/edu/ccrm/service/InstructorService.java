package edu.ccrm.service;

import edu.ccrm.domain.Instructor;
import java.util.ArrayList;
import java.util.List;

public class InstructorService {
    private final List<Instructor> instructors = new ArrayList<>();

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public List<Instructor> getAllInstructors() {
        return instructors;
    }

    public Instructor findByEmail(String email) {
        return instructors.stream()
                .filter(i -> i.getEmail().equalsIgnoreCase(email))
                .findFirst().orElse(null);
    }
}
