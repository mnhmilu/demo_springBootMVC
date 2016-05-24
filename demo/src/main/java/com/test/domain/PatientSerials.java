package com.test.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;

@Entity

public class PatientSerials {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;      
    
    
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date serialDate;  
    
    private String remarks;
    
    
    @Temporal(TemporalType.DATE)    
    private Date lastInsartedDate;      
    
    @ManyToOne   
    private PatientProfile patientProfile;    
    
    @NotNull
    private Integer serialNumber;   
	

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public PatientProfile getPatientProfile() {
		return patientProfile;
	}

	public void setPatientProfile(PatientProfile patientProfile) {
		this.patientProfile = patientProfile;
	}

	public Date getLastInsartedDate() {
		return lastInsartedDate;
	}

	public void setLastInsartedDate(Date lastInsartedDate) {
		this.lastInsartedDate = lastInsartedDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getSerialDate() {
		return serialDate;
	}

	public void setSerialDate(Date serialDate) {
		this.serialDate = serialDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
  
  
    
    
    
   
}