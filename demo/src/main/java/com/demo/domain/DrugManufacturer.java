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
@Table(name = "drugManufacturer")
public class DrugManufacturer {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="manufacturerId")
    private Integer manufacturerId;      
    
    
    @NotNull
    @NotBlank
    @Column(name = "manufacturer" ,nullable = false,unique=true)
    private String manufacturer;
   
  
    @OneToOne
    private Drug drug;	

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Integer getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	

    
   
}