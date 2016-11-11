package com.demo.commands;


import com.demo.domain.DrugBrand;
import com.demo.domain.DrugGeneric;



public class DrugSearchForm {		 
    
 

    private String drugName; 
    
    
    private DrugBrand drugBrand;
    
    private DrugGeneric drugGeneric;
    
    private Integer genericId;  
    
    
    private Integer brandId;   
    
    

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

	public DrugBrand getDrugBrand() {
		return drugBrand;
	}

	public void setDrugBrand(DrugBrand drugBrand) {
		this.drugBrand = drugBrand;
	}

	public DrugGeneric getDrugGeneric() {
		return drugGeneric;
	}

	public void setDrugGeneric(DrugGeneric drugGeneric) {
		this.drugGeneric = drugGeneric;
	}
    
   
    
   
   
}