package edu.ccrm.domain;

import java.time.LocalDate;

public abstract class Person{
    private String id;
    private String fullName;
    private String email;
    private LocalDate createdAt;

    public Person(String id, String fullName, String email){
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = LocalDate.now();
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public LocalDate getCreatedAt() { return createdAt; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }

    public abstract String getProfile();
}