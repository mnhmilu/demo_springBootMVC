package com.demo.services;


import java.util.Date;
import java.util.List;

import com.demo.commands.PatientSerialForm;
import com.demo.domain.PatientProfile;
import com.demo.domain.PatientSerials;

public interface PatientProfiletService {
    
    void savePatientSerial(PatientSerials patientSerials);   
    
    List<PatientSerialForm> searchPatientSerialInformationbyDate(Date searchDate);    
    
    void savePatientInfoWithSerail(PatientProfile patientProfile,Date serialDate);
  
}
