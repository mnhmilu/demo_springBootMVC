package com.test.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;

@Entity
public class PatientProfile {	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;  
  
    
    @NotNull
    @NotBlank
    @Column(name = "name" ,nullable = false)
    private String name;   
  
    private Integer age;
    
    
  
    private String mobile;


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