package com.demo.commands;


import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;



public class DrugGenericForm {		
  
    private Integer idGeneric;        

    @NotNull
    @NotBlank
   
    private String genericName;    
    
    private String remarks;
	

	public Integer getIdGeneric() {
		return idGeneric;
	}

	public void setIdGeneric(Integer idGeneric) {
		this.idGeneric = idGeneric;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}  
    
    
    

    
}