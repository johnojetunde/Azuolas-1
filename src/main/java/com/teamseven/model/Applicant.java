package com.teamseven.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "applicants")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message =  "Firstname is required")
    @Column(nullable = false, length = 255)
    private String firstName;

    @NotNull(message = "Lastname is required")
    @Column(nullable = false, length = 255)
    private String lastName;

    @NotNull(message = "Email is required")
    @Column(nullable = false, length = 255)
    private String email;

    @Column(length = 255)
    private String phoneNumber;

    private String stack;

    private Long yearsOfExperience;

    private java.sql.Date applicationDateTime;

    private java.sql.Date dateOfBirth;

    private String userKey;


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Long getYearsOfExperience() { return yearsOfExperience; }

    public void setYearsOfExperience(Long yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Date getApplicationDateTime() { return applicationDateTime; }

    public void setApplicationDateTime(Date applicationDateTime) { this.applicationDateTime = applicationDateTime; }

    public Date getDateOfBirth() { return dateOfBirth; }

    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getUserKey() { return userKey; }

    public void setUserKey(String userKey) { this.userKey = userKey; }

    public String getStack() { return stack; }

    public void setStack(String stack) { this.stack = stack; }
}
