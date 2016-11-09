package com.demo.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "drug_generic")
public class DrugGeneric {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;      
    
    
    @NotNull
    @NotBlank
    @Column(name = "generic_name" ,nullable = false)
    private String genericName;
   


	public String getGenericName() {
		return genericName;
	}


	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
    
   
}