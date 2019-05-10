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
 * @author Ayeni Olusegun
 */
@Entity
@Table(name = "applicantStatus")
public class ApplicantStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long applicantId;
    
     private Long statusId;
     
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long id) {
        this.applicantId = id;
    }
    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long id) {
        this.statusId = id;
    }
    
}
