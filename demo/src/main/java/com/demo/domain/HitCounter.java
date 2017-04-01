package com.demo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "hitcounter")
public class HitCounter {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;      
    
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date counterDate;  
    
    @NotNull
    private Integer counter;


	public Integer getId() {
		return id;
	}


	public Date getCounterDate() {
		return counterDate;
	}


	public void setCounterDate(Date counterDate) {
		this.counterDate = counterDate;
	}


	public void setId(Integer id) {
		this.id = id;
	}





	public Integer getCounter() {
		return counter;
	}


	public void setCounter(Integer counter) {
		this.counter = counter;
	} 
    
    

}
