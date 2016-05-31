package com.test.services;


import java.util.Date;
import java.util.List;

import com.test.domain.PatientSerials;
import com.test.dto.PatientSerialDTO;

public interface PatientProfiletService {
    
    void savePatientSerial(PatientSerials patientSerials);   
    
    List<PatientSerialDTO> searchPatientSerialInformationbyDate(Date searchDate);    
    
    void savePatientInfoWithSerail(PatientSerialDTO dto);
  
}
