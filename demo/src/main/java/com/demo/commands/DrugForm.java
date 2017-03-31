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
    
    private String strength;  
    
    private String dosageForm;      
    
  
    private String storage;      

    private String packSize;
    
    @NotNull
    private Double drugprice;
    
    private byte[] image;  

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Integer getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
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

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getDosageForm() {
		return dosageForm;
	}

	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getPackSize() {
		return packSize;
	}

	public void setPackSize(String packSize) {
		this.packSize = packSize;
	}

	public Double getDrugprice() {
		return drugprice;
	}

	public void setDrugprice(Double drugprice) {
		this.drugprice = drugprice;
	}          
    

    
}