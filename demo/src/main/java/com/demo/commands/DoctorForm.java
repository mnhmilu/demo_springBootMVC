package com.demo.commands;


import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;



public class DoctorForm {		
    
    private Integer id;      
    
    @NotNull
    @NotBlank
  
    private String doctorName; 


    private String doctorDetails;
    

    private String chamber;
    

    private Integer idSpecialization;
    
    private String specializationName;   
    
    
    private Date insertDate; 
    
    
    private String insertedBy;
    
    
    


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public String getInsertedBy() {
		return insertedBy;
	}


	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}


	public String getSpecializationName() {
		return specializationName;
	}


	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
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


	public Integer getIdSpecialization() {
		return idSpecialization;
	}


	public void setIdSpecialization(Integer idSpecialization) {
		this.idSpecialization = idSpecialization;
	}    
    

    
}