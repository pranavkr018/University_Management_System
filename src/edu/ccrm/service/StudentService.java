package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Optional<Student> findByRegNo(String regNo) {
        return students.stream()
                .filter(s -> s.getRegNo().equalsIgnoreCase(regNo))
                .findFirst();
    }

    public boolean deactivateStudent(String regNo) {
        Optional<Student> studentOpt = findByRegNo(regNo);
        if (studentOpt.isPresent()) {
            studentOpt.get().deactivate();
            return true;
        }
        return false;
    }
}
