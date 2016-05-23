package com.test.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.domain.PatientProfile;
import com.test.domain.PatientSerials;
import com.test.dto.PatientSerialDTO;

@Repository
public interface PatientSerialRepository extends CrudRepository<PatientSerials, Integer>{
	
	List<PatientSerials> findByPatientProfile(PatientProfile patientProfile);
	
	List<PatientSerials> findBySerialDate(Date serialDate);
	
	
}
	
	