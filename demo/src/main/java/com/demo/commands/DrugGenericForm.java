package com.demo.commands;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class DrugGenericForm {

	private Integer idGeneric;

	@NotNull
	@NotBlank

	private String genericName;

	private String remarks;

	private String classification;

	private String safetyRemarks;

	private String indicationDosages;

	private String contraindication;

	private String advanceDrugReaction;

	private String interAction;	
	
	private String specialPrecaution;	
	


	public String getSpecialPrecaution() {
		return specialPrecaution;
	}

	public void setSpecialPrecaution(String specialPrecaution) {
		this.specialPrecaution = specialPrecaution;
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