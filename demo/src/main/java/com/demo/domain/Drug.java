package com.demo.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Column(name = "generic_name" ,nullable = false)
    private String genericName;  
    
    
    @NotNull
    @NotBlank
    @Column(name = "brand_name" ,nullable = false)
    private String brandName; 
    
  
    @NotNull
    @NotBlank
    @Column(name = "drug_name" ,nullable = false)
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
    	
    

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getGenericName() {
		return genericName;
	}


	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}


	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
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