package com.test.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "patient_profile")
public class PatientProfile {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;  
  
    
    @NotNull
    @NotBlank
    @Column(name = "name" ,nullable = false)
    private String name;   
  
    
   
    @Column(name = "age" ,nullable = false)    
    @Min(1)
    @Max(99)    
    
    @NotNull
    private Integer age;    
    
    
    @NotNull
    @NotBlank
    @Column(name = "mobile" ,nullable = false) 
    @Size(min=11, max=12)
    private String mobile;
    
    
    
    @Temporal(TemporalType.DATE)
    private Date lastInsartedDate;    
    
   

	public Date getLastInsartedDate() {
		return lastInsartedDate;
	}


	public void setLastInsartedDate(Date lastInsartedDate) {
		this.lastInsartedDate = lastInsartedDate;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	} 
    
    
    
    
    
   
}