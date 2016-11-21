package com.demo.commands;


import com.demo.domain.DrugGeneric;
import com.demo.domain.DrugManufacturer;



public class DrugSearchForm {		 
     

    private String drugName; 
        
    private String genericName;
        
    private DrugManufacturer drugBrand;
    
    private DrugGeneric drugGeneric;
    
    private Integer genericId;  
    
    
    private Integer manufacturerId;      
       
        
	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public Integer getGenericId() {
		return genericId;
	}

	public void setGenericId(Integer genericId) {
		this.genericId = genericId;
	}


	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public DrugManufacturer getDrugBrand() {
		return drugBrand;
	}

	public void setDrugBrand(DrugManufacturer drugBrand) {
		this.drugBrand = drugBrand;
	}	
	

	public Integer getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public DrugGeneric getDrugGeneric() {
		return drugGeneric;
	}

	public void setDrugGeneric(DrugGeneric drugGeneric) {
		this.drugGeneric = drugGeneric;
	}
    
   
    
   
   
}