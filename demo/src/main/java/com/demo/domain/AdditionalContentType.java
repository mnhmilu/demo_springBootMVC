package com.demo.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "additionalContentType")
public class AdditionalContentType {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idadditionalContentType")
    private Integer idadditionalContentType;      
    
    
    @NotNull
    @NotBlank
    @Column(name = "additionalContentTypeName" ,nullable = false,unique=true)
    private String additionalContentTypeName;
    
    @Temporal(TemporalType.DATE)
    private Date insertDate; 
   
    
    @OneToOne
    private Content content;
    
    
    private String remarks;

	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public Integer getIdadditionalContentType() {
		return idadditionalContentType;
	}


	public void setIdadditionalContentType(Integer idadditionalContentType) {
		this.idadditionalContentType = idadditionalContentType;
	}


	public String getAdditionalContentTypeName() {
		return additionalContentTypeName;
	}


	public void setAdditionalContentTypeName(String additionalContentTypeName) {
		this.additionalContentTypeName = additionalContentTypeName;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public Content getContent() {
		return content;
	}


	public void setContent(Content content) {
		this.content = content;
	}


    
   
}