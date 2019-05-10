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
@Table(name = "status")
public class Status {
   @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
   private String statusName;
   
   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getStatusName() {
        return statusName;
    }
     public void setCompanyName(String statusName) {
        this.statusName = statusName;
    }
}
