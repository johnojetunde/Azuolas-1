package com.teamseven.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "de_team")
public class DeTeam {
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

    private String country;

    private String userKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
}

