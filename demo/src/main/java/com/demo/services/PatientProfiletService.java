package com.demo.services;


import java.util.Date;
import java.util.List;

import com.demo.domain.PatientSerials;
import com.demo.dto.PatientSerialDTO;

public interface PatientProfiletService {
    
    void savePatientSerial(PatientSerials patientSerials);   
    
    List<PatientSerialDTO> searchPatientSerialInformationbyDate(Date searchDate);    
    
    void savePatientInfoWithSerail(PatientSerialDTO dto);
  
}
