package com.demo.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "doctors_info")
public class DoctorsInfo {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;      
    
    @NotNull
    @NotBlank
    @Column(name = "doctorName" ,nullable = false)
    private String doctorName; 

    @Column(name = "doctorDetails",columnDefinition="TEXT")
    private String doctorDetails;
    
    @Column(name = "chamber",columnDefinition="TEXT")
    private String chamber;
    
    @OneToOne
    @NotNull
    @JoinColumn(name="idspecialization")
    private DoctorsSpecialization doctorsSpecialization;
    
    @Temporal(TemporalType.DATE)
    private Date insertDate; 
    
    @Temporal(TemporalType.DATE)
    private Date updateDate; 
    
    @Column(name = "insertedBy")
    private String insertedBy;
    
    @Column(name = "updatedBy")
    private String updatedBy;  
    
    

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorDetails() {
		return doctorDetails;
	}

	public void setDoctorDetails(String doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

	public String getChamber() {
		return chamber;
	}

	public void setChamber(String chamber) {
		this.chamber = chamber;
	}

	public DoctorsSpecialization getDoctorsSpecialization() {
		return doctorsSpecialization;
	}

	public void setDoctorsSpecialization(DoctorsSpecialization doctorsSpecialization) {
		this.doctorsSpecialization = doctorsSpecialization;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}    
	
	
    
   
}