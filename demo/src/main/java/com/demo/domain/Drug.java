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

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "drug_info")
public class Drug {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;  
    
    
    @NotNull
    @NotBlank
    @Column(name = "drugName" ,nullable = false)
    private String drugName; 

    @Column(name = "overview",columnDefinition="LONGTEXT")
    private String overView;
    
    @Column(name = "side_effects",columnDefinition="LONGTEXT")
    private String sideEffects;
    
    @Column(name = "dosages",columnDefinition="LONGTEXT")
    private String dosages;
       
    
    @Column(name = "interaction",columnDefinition="LONGTEXT")
    private String interaction;
    
    @Column(name = "professionals",columnDefinition="LONGTEXT")
    private String professionals;      
       
    
    @Temporal(TemporalType.DATE)
    private Date insertDate;  
    
    @Type(type="yes_no")
    @Column(name = "discontinued")
    private Boolean discontinued;
    
    
    @OneToOne
    @NotNull
    @JoinColumn(name="idGeneric")
    private DrugGeneric drugGeneric;
    	
    @OneToOne
    @NotNull
    @JoinColumn(name="idbrand")
    private DrugBrand drugBrand;    
    
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;     
    
    

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}


	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}


	public DrugGeneric getDrugGeneric() {
		return drugGeneric;
	}


	public void setDrugGeneric(DrugGeneric drugGeneric) {
		this.drugGeneric = drugGeneric;
	}


	public DrugBrand getDrugBrand() {
		return drugBrand;
	}


	public void setDrugBrand(DrugBrand drugBrand) {
		this.drugBrand = drugBrand;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}	

	public Boolean getDiscontinued() {
		return discontinued;
	}


	public void setDiscontinued(Boolean discontinued) {
		this.discontinued = discontinued;
	}


	public String getDrugName() {
		return drugName;
	}


	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}


	public String getOverView() {
		return overView;
	}


	public void setOverView(String overView) {
		this.overView = overView;
	}


	public String getSideEffects() {
		return sideEffects;
	}


	public void setSideEffects(String sideEffects) {
		this.sideEffects = sideEffects;
	}


	public String getDosages() {
		return dosages;
	}


	public void setDosages(String dosages) {
		this.dosages = dosages;
	}


	public String getInteraction() {
		return interaction;
	}


	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}


	public String getProfessionals() {
		return professionals;
	}


	public void setProfessionals(String professionals) {
		this.professionals = professionals;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
	
	
	
    
   
}