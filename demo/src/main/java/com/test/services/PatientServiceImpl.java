package  com.test.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.PatientSerials;
import com.test.repositories.PatientSerialRepository;

@Service
public class PatientServiceImpl implements PatientProfiletService {		
   
    private PatientSerialRepository patientSerialRepository;

    @Autowired
    public void setPatientRepository(PatientSerialRepository patientSerialRepository) {
      
        this.patientSerialRepository=patientSerialRepository;
    }

	@Override
	public void savePatientSerial(PatientSerials patientSerial) {		
		
		List<PatientSerials> list=patientSerialRepository.findBySerialDate(patientSerial.getSerialDate());
		
		patientSerial.setSerialNumber(list.size()+1);		
		
		patientSerialRepository.save(patientSerial);	
		
		
	}
}
