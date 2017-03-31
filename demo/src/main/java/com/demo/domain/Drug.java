package com.demo.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "drug_info")
public class Drug {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;      
    
    @NotNull
    @NotBlank
    @Column(name = "drugName" ,nullable = false)
    private String drugName; 

    @Column(name = "strength")
    private String strength;      
    
    
    @Column(name = "storage")
    private String storage;       
 
    
    @Column(name = "packSize")    
    private String packSize;
    
    @Column(name = "dosageForm")    
    private String dosageForm;
    
    @Column(name = "drugPrice",precision=10, scale=2)
    @NotNull
    private Double drugprice;    
       
    
    @Temporal(TemporalType.DATE)
    private Date insertDate;  
    
    @Type(type="yes_no")
    @Column(name = "discontinued")
    private Boolean discontinued;
    
    
    @OneToOne
    @NotNull
    @JoinColumn(name="idgeneric")
    private DrugGeneric drugGeneric;    	
    
    
    @OneToOne
    @NotNull
    @JoinColumn(name="idManufacturer")
    private DrugManufacturer drugManufacturer;    	
     
    
    public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	@Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;
    
    
    @Column(name="image")
    @Lob
    private byte[] image;      


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDrugName() {
		return drugName;
	}


	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}


	public String getStrength() {
		return strength;
	}


	public void setStrength(String strength) {
		this.strength = strength;
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


	public String getDosageForm() {
		return dosageForm;
	}


	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}


	public Double getDrugprice() {
		return drugprice;
	}


	public void setDrugprice(Double drugprice) {
		this.drugprice = drugprice;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public Boolean getDiscontinued() {
		return discontinued;
	}


	public void setDiscontinued(Boolean discontinued) {
		this.discontinued = discontinued;
	}


	public DrugGeneric getDrugGeneric() {
		return drugGeneric;
	}


	public void setDrugGeneric(DrugGeneric drugGeneric) {
		this.drugGeneric = drugGeneric;
	}


	public DrugManufacturer getDrugManufacturer() {
		return drugManufacturer;
	}


	public void setDrugManufacturer(DrugManufacturer drugManufacturer) {
		this.drugManufacturer = drugManufacturer;
	}


	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}


	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}       

   
   
}