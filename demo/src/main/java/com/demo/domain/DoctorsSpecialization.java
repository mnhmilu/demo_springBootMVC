package com.demo.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "doctorsSpecialization")
public class DoctorsSpecialization {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idspecialization")
    private Integer idSpecialization;      
    
    
    @NotNull
    @NotBlank
    @Column(name = "specializationName" ,nullable = false,unique=true)
    private String specializationName;
    
    @Temporal(TemporalType.DATE)
    private Date insertDate; 
   
    
    @OneToOne
    private DoctorsInfo doctorsInfo;
    
    
    private String remarks;

	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public Integer getIdSpecialization() {
		return idSpecialization;
	}


	public void setIdSpecialization(Integer idSpecialization) {
		this.idSpecialization = idSpecialization;
	}


	public String getSpecializationName() {
		return specializationName;
	}


	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public DoctorsInfo getDoctorsInfo() {
		return doctorsInfo;
	}


	public void setDoctorsInfo(DoctorsInfo doctorsInfo) {
		this.doctorsInfo = doctorsInfo;
	}

    
   
}