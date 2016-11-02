package com.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.commands.PatientSerialForm;
import com.demo.domain.PatientProfile;
import com.demo.domain.PatientSerials;

@Repository
public interface PatientSerialRepository extends CrudRepository<PatientSerials, Integer>{
	
	List<PatientSerials> findByPatientProfile(PatientProfile patientProfile);
	
	List<PatientSerials> findBySerialDate(Date serialDate);
	
	
}
	
	