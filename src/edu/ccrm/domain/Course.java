package edu.ccrm.domain;

public class Course {

    private String code;
    private String title;
    private int credits;
    private Instructor instructor;
    private Semester semester;
    private String department;

    public Course(String code, String title, int credits, Semester semester, String department) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.semester = semester;
        this.department = department;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public Semester getSemester() {
        return semester;
    }

    public String getDepartment() {
        return department;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return code + " - " + title
                + " (" + credits + " credits, " + semester
                + ", Instructor: " + (instructor != null ? instructor.getFullName() : "TBA") + ")";
    }

}
