package com.demo.commands;


import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.demo.domain.DrugGeneric;



public class DrugForm {		
  
    private Integer id;        

    private Integer genericId;  
    
    private String genericName;    
    
    private String manufacturer;    
    
    private Integer manufacturerId;
      
    @NotNull
    @NotBlank
    private String drugName; 

    private DrugGeneric drugGeneric;       
    

    private String composition;
    

    private String indication;
    

    private String dosagesAdministration;
       
 
    private String contraindication;
    

    private String sideEffect;          
    
   
    private String specialWarningPrecautions;    
    
  
    private String drugInteraction;        
    
  
    private String storage;  
    

    private String pregnancyLactation;    
    

    private String packSize;
    
    @NotNull
    private Double drugprice;      


	public String getPackSize() {
		return packSize;
	}


	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}


	public Integer getManufacturerId() {
		return manufacturerId;
	}


	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
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


	public String getGenericName() {
		return genericName;
	}


	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}


	public String getDrugName() {
		return drugName;
	}


	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}


	public DrugGeneric getDrugGeneric() {
		return drugGeneric;
	}


	public void setDrugGeneric(DrugGeneric drugGeneric) {
		this.drugGeneric = drugGeneric;
	}


	public String getComposition() {
		return composition;
	}


	public void setComposition(String composition) {
		this.composition = composition;
	}


	public String getIndication() {
		return indication;
	}


	public void setIndication(String indication) {
		this.indication = indication;
	}


	public String getDosagesAdministration() {
		return dosagesAdministration;
	}


	public void setDosagesAdministration(String dosagesAdministration) {
		this.dosagesAdministration = dosagesAdministration;
	}


	public String getContraindication() {
		return contraindication;
	}


	public void setContraindication(String contraindication) {
		this.contraindication = contraindication;
	}


	public String getSideEffect() {
		return sideEffect;
	}


	public void setSideEffect(String sideEffect) {
		this.sideEffect = sideEffect;
	}


	public String getSpecialWarningPrecautions() {
		return specialWarningPrecautions;
	}


	public void setSpecialWarningPrecautions(String specialWarningPrecautions) {
		this.specialWarningPrecautions = specialWarningPrecautions;
	}


	public String getDrugInteraction() {
		return drugInteraction;
	}


	public void setDrugInteraction(String drugInteraction) {
		this.drugInteraction = drugInteraction;
	}


	public String getStorage() {
		return storage;
	}


	public void setStorage(String storage) {
		this.storage = storage;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getPregnancyLactation() {
		return pregnancyLactation;
	}


	public void setPregnancyLactation(String pregnancyLactation) {
		this.pregnancyLactation = pregnancyLactation;
	}


	public Double getDrugprice() {
		return drugprice;
	}


	public void setDrugprice(Double drugprice) {
		this.drugprice = drugprice;
	}    
    
    
    
       
    
    

    
}