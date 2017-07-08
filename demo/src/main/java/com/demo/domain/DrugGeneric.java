package com.demo.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "drugGeneric")
public class DrugGeneric {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idgeneric")
    private Integer idGeneric;      
    
    
    @NotNull
    @NotBlank
    @Column(name = "generic_name" ,nullable = false,unique=true)
    private String genericName;
    
    @Temporal(TemporalType.DATE)
    private Date insertDate;   
    

    @OneToOne
    private Drug drug;
    
    @Column(name = "classification",columnDefinition="TEXT")
    private String classification;
    
    @Column(name = "safetyRemarks",columnDefinition="TEXT")
    private String safetyRemarks;
    
    @Column(name = "indicationDosages",columnDefinition="TEXT")
    private String indicationDosages;
    
    
    // Safety Alert Components
    
  
    
    @Column(name = "contraindication",columnDefinition="TEXT")
    private String contraindication;
    
    @Column(name = "specialPrecaution",columnDefinition="TEXT")
    private String specialPrecaution;
    
    
    @Column(name = "advanceDrugReaction",columnDefinition="TEXT")
    private String advanceDrugReaction;
    
    
    @Column(name = "interAction",columnDefinition="TEXT")
    private String interAction;
    
    
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;
    
    
    
    
    
    private String remarks;   
    
    
    
    
    

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}


	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}


	public String getClassification() {
		return classification;
	}


	public void setClassification(String classification) {
		this.classification = classification;
	}


	public String getSafetyRemarks() {
		return safetyRemarks;
	}


	public void setSafetyRemarks(String safetyRemarks) {
		this.safetyRemarks = safetyRemarks;
	}


	public String getIndicationDosages() {
		return indicationDosages;
	}


	public void setIndicationDosages(String indicationDosages) {
		this.indicationDosages = indicationDosages;
	}


	public String getContraindication() {
		return contraindication;
	}


	public void setContraindication(String contraindication) {
		this.contraindication = contraindication;
	}


	public String getSpecialPrecaution() {
		return specialPrecaution;
	}


	public void setSpecialPrecaution(String specialPrecaution) {
		this.specialPrecaution = specialPrecaution;
	}


	public String getAdvanceDrugReaction() {
		return advanceDrugReaction;
	}


	public void setAdvanceDrugReaction(String advanceDrugReaction) {
		this.advanceDrugReaction = advanceDrugReaction;
	}


	public String getInterAction() {
		return interAction;
	}


	public void setInterAction(String interAction) {
		this.interAction = interAction;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getGenericName() {
		return genericName;
	}


	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public Integer getIdGeneric() {
		return idGeneric;
	}


	public void setIdGeneric(Integer idGeneric) {
		this.idGeneric = idGeneric;
	}


	public Drug getDrug() {
		return drug;
	}


	public void setDrug(Drug drug) {
		this.drug = drug;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
	

    
   
}