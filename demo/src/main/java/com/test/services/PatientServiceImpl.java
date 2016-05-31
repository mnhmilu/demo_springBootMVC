package  com.test.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.PatientProfile;
import com.test.domain.PatientSerials;
import com.test.dto.PatientSerialDTO;
import com.test.repositories.PatientRepository;
import com.test.repositories.PatientSerialRepository;

@Service
public class PatientServiceImpl implements PatientProfiletService {		
   
    private PatientSerialRepository patientSerialRepository;
    private PatientRepository patientRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository ,PatientSerialRepository patientSerialRepository) {
      
        this.patientSerialRepository=patientSerialRepository;
        this.patientRepository=patientRepository;
    }

	@Override
	public void savePatientSerial(PatientSerials patientSerial) {		
		
		List<PatientSerials> list=patientSerialRepository.findBySerialDate(patientSerial.getSerialDate());
		
		patientSerial.setSerialNumber(list.size()+1);		
		
		patientSerialRepository.save(patientSerial);			
		
	}
	
	@Override
	public void savePatientInfoWithSerail(PatientSerialDTO dto) {		
		
		
		PatientProfile patient = new PatientProfile();
		
		patient.setAge(dto.getAge());
		patient.setName(dto.getName());
		patient.setMobile(dto.getMobile());
		patient.setLastInsartedDate(new Date());
		patientRepository.save(patient);
		
		List<PatientSerials> list=patientSerialRepository.findBySerialDate(dto.getSerialDate());
		
		PatientSerials oneSerial = new PatientSerials();
        oneSerial.setLastInsartedDate(new Date());        
        oneSerial.setRemarks("test");
        oneSerial.setSerialNumber(list.size()+1);
        oneSerial.setPatientProfile(patient);
        oneSerial.setSerialDate(dto.getSerialDate());        
        patientSerialRepository.save(oneSerial);		
		
	}

	@Override
	public List<PatientSerialDTO> searchPatientSerialInformationbyDate(Date searchDate) {	
		
		List<PatientSerialDTO> resultList = new ArrayList<PatientSerialDTO>();
		
		List<PatientSerials> list=patientSerialRepository.findBySerialDate(searchDate);		
		
		int counter=1;
		
		for (PatientSerials item: list)
		{
			PatientSerialDTO dto =new PatientSerialDTO();
			dto.setItem_no(counter);
			dto.setName(item.getPatientProfile().getName());
			dto.setMobile(item.getPatientProfile().getMobile());
			dto.setSerialDate(item.getSerialDate());
			dto.setSerialNumber(item.getSerialNumber());	
			dto.setRemarks(item.getRemarks());
			dto.setAge(item.getPatientProfile().getAge());
			resultList.add(dto);
			counter++;
		}		
		
		return resultList;
	}
}
