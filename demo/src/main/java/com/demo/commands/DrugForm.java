package com.demo.commands;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.demo.domain.DrugBrand;
import com.demo.domain.DrugGeneric;



public class DrugForm {		
  
    private Integer id;    
    
    //@NotNull
   // @NotBlank 
    private Integer genericId;  
    
    private String genericName;
    
    
   // @NotNull
   // @NotBlank
    private Integer brandId; 
    
    private String brandName;
    
  
    @NotNull
    @NotBlank
    private String drugName; 


    private String overView;

    private String sideEffects;    

    private String dosages;
    
    private String interaction;    
 
    private String professionals;      
    
    private Boolean discontinued;  
    
    

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


	public Boolean getDiscontinued() {
		return discontinued;
	}


	public void setDiscontinued(Boolean discontinued) {
		this.discontinued = discontinued;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getGenericId() {
		return genericId;
	}


	public void setGenericId(Integer genericId) {
		this.genericId = genericId;
	}


	public Integer getBrandId() {
		return brandId;
	}


	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
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
   
}