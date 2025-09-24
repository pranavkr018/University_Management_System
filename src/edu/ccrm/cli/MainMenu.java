package edu.ccrm.cli;

import edu.ccrm.domain.*;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainMenu {

    private ImportExportService ioService;
    private BackupService backupService;

    public MainMenu() {
        try {
            ioService = new ImportExportService(Paths.get("../data"));
        } catch (Exception e) {
            System.out.println("❌ Failed to initialize IO service: " + e.getMessage());
        }

        try {
            ioService = new ImportExportService(Paths.get("../data"));
            backupService = new BackupService(Paths.get("../data"), Paths.get("../backup"));
        } catch (Exception e) {
            System.out.println("❌ Failed to initialize IO/Backup service: " + e.getMessage());
        }
    }

    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService();
    private final TranscriptService transcriptService = new TranscriptService();
    private InstructorService instructorService = new InstructorService();

    private final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new MainMenu().run();
    }

    private void run() {
        int choice;
        do {
            printMenu();
            choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (choice) {
                case 1 ->
                    addStudent();
                case 2 ->
                    listStudents();
                case 3 ->
                    addCourse();
                case 4 ->
                    listCourses();
                case 5 ->
                    enrollStudent();
                case 6 ->
                    listEnrollments();
                case 7 ->
                    assignGrade();
                case 8 ->
                    printTranscript();
                case 9 ->
                    exportData();
                case 10 ->
                    backupData();
                case 11 ->
                    importData();
                case 12 ->
                    addInstructor();
                case 13 ->
                    listInstructors();
                case 14 ->
                    assignInstructorToCourse();
                case 15 ->
                    listCoursesByInstructor();
                case 0 ->
                    System.out.println("Exiting...");
                default ->
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private void printMenu() {
        System.out.println("\n===== CAMPUS COURSE & RECORDS MANAGER =====");
        System.out.println("1. Add Student");
        System.out.println("2. List Students");
        System.out.println("3. Add Course");
        System.out.println("4. List Courses");
        System.out.println("5. Enroll Student in Course");
        System.out.println("6. List Enrollments");
        System.out.println("7. Assign Grade to Enrollment");
        System.out.println("8. Print Student Transcript");
        System.out.println("9. Export Data to Files");
        System.out.println("10. Backup Data and Show Size");
        System.out.println("11. Import Data from Files");
        System.out.println("12. Add Instructor");
        System.out.println("13. List Instructors");
        System.out.println("14. Assign Instructor to Course");
        System.out.println("15. List Courses by Instructor");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Full Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Registration No: ");
        String regNo = sc.nextLine();

        Student student = new Student(id, name, email, regNo);
        studentService.addStudent(student);
        System.out.println("✅ Student added successfully!");
    }

    private void listStudents() {
        System.out.println("\n--- All Students ---");
        studentService.getAllStudents().forEach(System.out::println);
    }

    private void addCourse() {
        System.out.print("Enter Course Code: ");
        String code = sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Credits: ");
        int credits = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.println("Select Semester: (1) FALL (2) WINTER (3) INTERIM");
        int semChoice = sc.nextInt();
        sc.nextLine();

        Semester sem = switch (semChoice) {
            case 1 ->
                Semester.FALL;
            case 2 ->
                Semester.WINTER;
            case 3 ->
                Semester.INTERIM;
            default ->
                Semester.FALL;
        };

        Course course = new Course(code, title, credits, sem, dept);
        courseService.addCourse(course);
        System.out.println("✅ Course added successfully!");
    }

    private void listCourses() {
        var courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("\n=== Courses ===");
        courses.forEach(System.out::println); // uses toString()
    }

    private void enrollStudent() {
        System.out.print("Enter Student RegNo: ");
        String regNo = sc.nextLine();
        var studentOpt = studentService.findByRegNo(regNo);
        if (studentOpt.isEmpty()) {
            System.out.println("❌ Student not found!");
            return;
        }

        System.out.print("Enter Course Code: ");
        String code = sc.nextLine();
        var courseOpt = courseService.findByCode(code);
        if (courseOpt.isEmpty()) {
            System.out.println("❌ Course not found!");
            return;
        }

        enrollmentService.enroll(studentOpt.get(), courseOpt.get());
        System.out.println("✅ Enrollment successful!");
    }

    private void listEnrollments() {
        System.out.println("\n--- All Enrollments ---");
        enrollmentService.getAllEnrollments().forEach(System.out::println);
    }

    private void printTranscript() {
        System.out.print("Enter Student RegNo: ");
        String regNo = sc.nextLine();
        var studentOpt = studentService.findByRegNo(regNo);

        if (studentOpt.isEmpty()) {
            System.out.println("❌ Student not found!");
            return;
        }

        var enrollments = enrollmentService.getEnrollmentsForStudent(studentOpt.get());
        transcriptService.printTranscript(studentOpt.get(), enrollments);
    }

    private void assignGrade() {
        System.out.print("Enter Student RegNo: ");
        String regNo = sc.nextLine();
        var studentOpt = studentService.findByRegNo(regNo);

        if (studentOpt.isEmpty()) {
            System.out.println("❌ Student not found!");
            return;
        }

        var enrollments = enrollmentService.getEnrollmentsForStudent(studentOpt.get());
        if (enrollments.isEmpty()) {
            System.out.println("❌ No enrollments found for this student.");
            return;
        }

        System.out.println("\n--- Enrollments ---");
        for (int i = 0; i < enrollments.size(); i++) {
            System.out.println((i + 1) + ". " + enrollments.get(i));
        }

        System.out.print("Select enrollment number: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > enrollments.size()) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        var selectedEnrollment = enrollments.get(choice - 1);

        System.out.println("Select Grade: ");
        Grade[] grades = Grade.values();
        for (int i = 0; i < grades.length; i++) {
            System.out.println((i + 1) + ". " + grades[i]);
        }

        System.out.print("Enter grade choice: ");
        int gradeChoice = sc.nextInt();
        sc.nextLine();

        if (gradeChoice < 1 || gradeChoice > grades.length) {
            System.out.println("❌ Invalid grade choice.");
            return;
        }

        selectedEnrollment.assignGrade(grades[gradeChoice - 1]);
        System.out.println("✅ Grade assigned successfully!");
    }

    private void exportData() {
        try {
            ioService.exportStudents(studentService.getAllStudents());
            ioService.exportCourses(courseService.getAllCourses());
            ioService.exportInstructors(instructorService.getAllInstructors());
            ioService.exportEnrollments(enrollmentService.getAllEnrollments());
            System.out.println("✅ Data exported to ../data folder.");
        } catch (Exception e) {
            System.out.println("❌ Export failed: " + e.getMessage());
        }
    }

    private void backupData() {
        try {
            Path backupDir = backupService.createBackup();
            long size = backupService.computeDirectorySize(backupDir);
            System.out.println("✅ Backup created at: " + backupDir);
            System.out.println("Total backup size: " + size + " bytes");
        } catch (Exception e) {
            System.out.println("❌ Backup failed: " + e.getMessage());
        }
    }

    private void importData() {
        try {
            var importedStudents = ioService.importStudents();
            importedStudents.forEach(studentService::addStudent);

            var importedInstructors = ioService.importInstructors();
            importedInstructors.forEach(instructorService::addInstructor);

            var importedCourses = ioService.importCourses(importedInstructors);
            importedCourses.forEach(courseService::addCourse);

            var importedEnrollments = ioService.importEnrollments(
                    studentService.getAllStudents(),
                    courseService.getAllCourses());
            importedEnrollments.forEach(e -> enrollmentService.getAllEnrollments().add(e));

            System.out.println("✅ Data imported successfully!");
        } catch (Exception e) {
            System.out.println("❌ Import failed: " + e.getMessage());
        }
    }

    private void addInstructor() {
        System.out.print("Enter Instructor ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Full Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();

        instructorService.addInstructor(new Instructor(id, name, email, dept));
        System.out.println("✅ Instructor added!");
    }

    private void listInstructors() {
        var instructors = instructorService.getAllInstructors();
        if (instructors.isEmpty()) {
            System.out.println("No instructors found.");
            return;
        }
        instructors.forEach(System.out::println);
    }

    private void assignInstructorToCourse() {
        System.out.print("Enter Course Code: ");
        String code = sc.nextLine();
        var course = courseService.findByCode(code).orElse(null);
        if (course == null) {
            System.out.println("❌ Course not found.");
            return;
        }

        System.out.print("Enter Instructor Email: ");
        String email = sc.nextLine();
        var instructor = instructorService.findByEmail(email);
        if (instructor == null) {
            System.out.println("❌ Instructor not found.");
            return;
        }

        course.setInstructor(instructor);
        System.out.println("✅ Instructor assigned to course!");
    }

    private void listCoursesByInstructor() {
        System.out.print("Enter Instructor Email: ");
        String email = sc.nextLine();
        var instructor = instructorService.findByEmail(email);

        if (instructor == null) {
            System.out.println("❌ Instructor not found.");
            return;
        }

        System.out.println("\nCourses taught by " + instructor.getFullName() + ":");
        var courses = courseService.getAllCourses().stream()
                .filter(c -> c.getInstructor() != null
                && c.getInstructor().getEmail().equalsIgnoreCase(email))
                .toList();

        if (courses.isEmpty()) {
            System.out.println("No courses assigned to this instructor.");
            return;
        }

        courses.forEach(System.out::println);
    }

}
