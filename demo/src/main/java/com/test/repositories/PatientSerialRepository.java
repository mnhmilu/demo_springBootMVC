package com.test.repositories;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.test.domain.PatientProfile;
import com.test.domain.PatientSerials;
import com.test.domain.Product;

@Repository
public interface PatientSerialRepository extends CrudRepository<PatientSerials, Integer>{
	
	List<PatientSerials> findByPatientProfile(PatientProfile patientProfile);
	
	
}
	
	