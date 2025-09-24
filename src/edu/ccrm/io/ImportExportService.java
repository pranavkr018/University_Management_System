package edu.ccrm.io;

import edu.ccrm.domain.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImportExportService {

    private final Path dataDir;

    public ImportExportService(Path dataDir) throws IOException {
        this.dataDir = dataDir;
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }
    }

    // ================= EXPORT =================

    public void exportStudents(List<Student> students) throws IOException {
        Path file = dataDir.resolve("students.csv");
        List<String> lines = students.stream()
                .map(s -> String.join(",",
                        s.getId(),
                        s.getRegNo(),
                        s.getFullName(),
                        s.getEmail(),
                        String.valueOf(s.isActive())))
                .toList();
        Files.write(file, lines);
    }

    public void exportInstructors(List<Instructor> instructors) throws IOException {
        Path file = dataDir.resolve("instructors.csv");
        List<String> lines = instructors.stream()
                .map(i -> String.join(",",
                        i.getId(),
                        i.getFullName(),
                        i.getEmail(),
                        i.getDepartment()))
                .toList();
        Files.write(file, lines);
    }

    public void exportCourses(List<Course> courses) throws IOException {
        Path file = dataDir.resolve("courses.csv");
        List<String> lines = courses.stream()
                .map(c -> String.join(",",
                        c.getCode(),
                        c.getTitle(),
                        String.valueOf(c.getCredits()),
                        c.getDepartment(),
                        c.getSemester().name(),
                        (c.getInstructor() != null ? c.getInstructor().getEmail() : "")))
                .toList();
        Files.write(file, lines);
    }

    public void exportEnrollments(List<Enrollment> enrollments) throws IOException {
        Path file = dataDir.resolve("enrollments.csv");
        List<String> lines = enrollments.stream()
                .map(e -> String.join(",",
                        e.getStudent().getRegNo(),
                        e.getCourse().getCode(),
                        e.getEnrolledDate().toString(),
                        (e.getGrade() != null ? e.getGrade().name() : "")))
                .toList();
        Files.write(file, lines);
    }

    // ================= IMPORT =================

    public List<Student> importStudents() throws IOException {
        Path file = dataDir.resolve("students.csv");
        List<Student> students = new ArrayList<>();
        if (!Files.exists(file)) return students;

        for (String line : Files.readAllLines(file)) {
            String[] parts = line.split(",");
            if (parts.length >= 5) {
                Student s = new Student(parts[0].trim(), parts[2].trim(), parts[3].trim(), parts[1].trim());
                if (!Boolean.parseBoolean(parts[4].trim())) s.deactivate();
                students.add(s);
            }
        }
        return students;
    }

    public List<Instructor> importInstructors() throws IOException {
        Path file = dataDir.resolve("instructors.csv");
        List<Instructor> instructors = new ArrayList<>();
        if (!Files.exists(file)) return instructors;

        for (String line : Files.readAllLines(file)) {
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                instructors.add(new Instructor(
                        parts[0].trim(),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim()));
            }
        }
        return instructors;
    }

    public List<Course> importCourses(List<Instructor> instructors) throws IOException {
        Path file = dataDir.resolve("courses.csv");
        List<Course> courses = new ArrayList<>();
        if (!Files.exists(file)) return courses;

        for (String line : Files.readAllLines(file)) {
            String[] parts = line.split(",");
            if (parts.length >= 5) {
                Course c = new Course(
                        parts[0].trim(),
                        parts[1].trim(),
                        Integer.parseInt(parts[2].trim()),
                        Semester.valueOf(parts[4].trim().toUpperCase()),
                        parts[3].trim());

                // If instructor email present, find instructor & assign
                if (parts.length >= 6 && !parts[5].trim().isEmpty()) {
                    String email = parts[5].trim();
                    Instructor instructor = instructors.stream()
                            .filter(i -> i.getEmail().equalsIgnoreCase(email))
                            .findFirst().orElse(null);
                    if (instructor != null) c.setInstructor(instructor);
                }

                courses.add(c);
            }
        }
        return courses;
    }

    public List<Enrollment> importEnrollments(List<Student> students, List<Course> courses) throws IOException {
        Path file = dataDir.resolve("enrollments.csv");
        List<Enrollment> enrollments = new ArrayList<>();
        if (!Files.exists(file)) return enrollments;

        for (String line : Files.readAllLines(file)) {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                Student student = students.stream()
                        .filter(s -> s.getRegNo().equalsIgnoreCase(parts[0].trim()))
                        .findFirst().orElse(null);
                Course course = courses.stream()
                        .filter(c -> c.getCode().equalsIgnoreCase(parts[1].trim()))
                        .findFirst().orElse(null);

                if (student != null && course != null) {
                    Enrollment e = new Enrollment(student, course);
                    if (parts.length >= 4 && !parts[3].trim().isEmpty()) {
                        try {
                            e.assignGrade(Grade.valueOf(parts[3].trim().toUpperCase()));
                        } catch (IllegalArgumentException ex) {
                            System.out.println("⚠️ Skipping invalid grade: " + parts[3]);
                        }
                    }
                    student.enrollCourse(course);
                    enrollments.add(e);
                }
            }
        }
        return enrollments;
    }
}
