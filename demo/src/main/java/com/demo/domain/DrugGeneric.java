package com.demo.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "drugGeneric")
public class DrugGeneric {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idgeneric")
    private Integer idGeneric;      
    
    
    @NotNull
    @NotBlank
    @Column(name = "generic_name" ,nullable = false)
    private String genericName;
    
    @Temporal(TemporalType.DATE)
    private Date insertDate; 
   
    
    //@OneToOne(fetch=FetchType.LAZY, mappedBy="drugGeneric", orphanRemoval = true)
    @OneToOne
    private Drug drug;
    
    
    private String remarks;

	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getGenericName() {
		return genericName;
	}


	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public Integer getIdGeneric() {
		return idGeneric;
	}


	public void setIdGeneric(Integer idGeneric) {
		this.idGeneric = idGeneric;
	}


	public Drug getDrug() {
		return drug;
	}


	public void setDrug(Drug drug) {
		this.drug = drug;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
	

    
   
}