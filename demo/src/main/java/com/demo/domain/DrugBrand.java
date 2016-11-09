package com.demo.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "drug_brand")
public class DrugBrand {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;      
    
    
    @NotNull
    @NotBlank
    @Column(name = "brand_name" ,nullable = false)
    private String brandName;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	} 
    
  
	
	
    
   
}