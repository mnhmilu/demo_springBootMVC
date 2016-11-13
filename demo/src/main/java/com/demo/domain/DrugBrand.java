package com.demo.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "drugBrand")
public class DrugBrand {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idbrand")
    private Integer idBrand;      
    
    
    @NotNull
    @NotBlank
    @Column(name = "brand_name" ,nullable = false)
    private String brandName;  
    
    
    @OneToOne(fetch=FetchType.LAZY, mappedBy="drugBrand")
    private Drug drug;


	public Integer getIdBrand() {
		return idBrand;
	}


	public void setIdBrand(Integer idBrand) {
		this.idBrand = idBrand;
	}


	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}


	public Drug getDrug() {
		return drug;
	}


	public void setDrug(Drug drug) {
		this.drug = drug;
	}    
    

    
    

   
}