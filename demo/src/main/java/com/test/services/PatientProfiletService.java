package com.test.services;


import javax.persistence.Query;

import com.test.domain.PatientProfile;
import com.test.domain.PatientSerials;
import com.test.domain.Product;

public interface PatientProfiletService {
    
	// will be removed, use repository instead
	
	Iterable<PatientProfile> listAllPatients();

    PatientProfile getPatientProfileById(Integer id);

    PatientProfile savePatientProfile(PatientProfile product);

    void deletePatientProfile(Integer id);
    
    Iterable<PatientProfile> getPatientProfilebySearch(String mobile);
    
    ///
    
    void savePatientSerial(PatientSerials patientSerials);
    
    
  
}
