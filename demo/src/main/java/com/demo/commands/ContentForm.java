package com.demo.commands;


import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;



public class ContentForm {		
  
    private Integer id;        

    @NotNull
    @NotBlank  
    private String contentType;    
    
    @NotNull
    @NotBlank  
    private String header; 
    
    @NotNull
    @NotBlank  
    private String content_summary;        
   
  
    private String content_details;


    private String add_section;
    

    private String imageURL;
    

    private Date insertDate;    
       
    
    @NotNull
    private Date expireDate;



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



	public String getAdd_section() {
		return add_section;
	}



	public void setAdd_section(String add_section) {
		this.add_section = add_section;
	}



	public String getImageURL() {
		return imageURL;
	}



	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
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
       
    
    

    
}