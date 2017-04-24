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

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "content_info")
public class Content {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;          
    
    @NotNull
    @NotBlank    
    @Column(name = "contentType",columnDefinition="TEXT" ,nullable = false)
    private String contentType;    
    
    @NotNull
    @NotBlank
    @Column(name = "header" ,nullable = false)
    private String header; 
    
    @NotNull
    @NotBlank
    @Column(name = "content_summary",columnDefinition="TEXT" ,nullable = false)
    private String content_summary;        
   
    @Column(name = "content_details",columnDefinition="TEXT" )
    private String content_details;

    @Column(name = "addSection")
    private String addSection;
    
    @Column(name = "contentPage")
    private String contentPage;    
    
    
    @Column(name = "drugUpdateType")
    private String drugUpdateType;
    
    @Column(name = "originalFileName")
    private String originalFileName;
    
    
    @Temporal(TemporalType.DATE)
    private Date insertDate;    
       
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date expireDate;   
   
    
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedDate;
    
    @Column(name="image")
    @Lob
    private byte[] image;      


    @OneToOne  
    @JoinColumn(name="idadditionalContentType")
    private AdditionalContentType additionalContentType;
    
    
    

	public AdditionalContentType getAdditionalContentType() {
		return additionalContentType;
	}


	public void setAdditionalContentType(AdditionalContentType additionalContentType) {
		this.additionalContentType = additionalContentType;
	}


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public String getOriginalFileName() {
		return originalFileName;
	}


	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	public String getHeader() {
		return header;
	}


	public void setHeader(String header) {
		this.header = header;
	}


	public String getContent_summary() {
		return content_summary;
	}


	public void setContent_summary(String content_summary) {
		this.content_summary = content_summary;
	}


	public String getContent_details() {
		return content_details;
	}


	public void setContent_details(String content_details) {
		this.content_details = content_details;
	}




	public String getAddSection() {
		return addSection;
	}


	public void setAddSection(String addSection) {
		this.addSection = addSection;
	}
	

	public String getContentPage() {
		return contentPage;
	}


	public void setContentPage(String contentPage) {
		this.contentPage = contentPage;
	}


	public String getDrugUpdateType() {
		return drugUpdateType;
	}


	public void setDrugUpdateType(String drugUpdateType) {
		this.drugUpdateType = drugUpdateType;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public Date getExpireDate() {
		return expireDate;
	}


	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}


	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}


	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}       


   
}