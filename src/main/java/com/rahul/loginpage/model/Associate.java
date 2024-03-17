package com.rahul.loginpage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "associate_table")
public class Associate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String associateIds;

    private boolean hasPermission;

	
    
//    public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

//	public String getAssociateId() {
//		return associateId;
//	}
//
//	public void setAssociateId(String associateId) {
//		this.associateId = associateId;
//	}
//
//	public boolean isHasPermission() {
//		return hasPermission;
//	}
//
//	public void setHasPermission(boolean hasPermission) {
//		this.hasPermission = hasPermission;
//	}
    
    
    
}
