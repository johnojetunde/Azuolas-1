/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamseven.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Chris Uzor
 */
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message =  "Company Name is required")
    @Column(nullable = false, length = 255)
    private String companyName;

    @NotNull(message = "Email is required")
    @Column(nullable = false, length = 255)
    private String email;

    @NotNull(message = "Type is required")
    @Column(nullable = false, length = 255)
    private String companyType;

    @NotNull(message = "Stack is required")
    @Column(nullable = false, length = 255)
    private String companyStack;

    private String companyKey;

    private String companyLocation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType(){
        return companyType;
    }
    public void setCompanyType(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyStack(){
        return companyType;
    }
    public void setCompanyStack(String companyStack) {
        this.companyStack = companyStack;
    }

    public String getCompanylocation(){
        return companyLocation;
    }
    public void setcompanyLocation(String companyStack) {
        this.companyStack = companyStack;
    }

}
