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
@Table(name = "applicant_status")
public class ApplicantStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "applicant_id", referencedColumnName = "id", nullable = false)
    private Applicant applicant;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
