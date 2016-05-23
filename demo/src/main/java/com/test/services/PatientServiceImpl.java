package  com.test.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.PatientSerials;
import com.test.dto.PatientSerialDTO;
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

	@Override
	public List<PatientSerialDTO> searchPatientSerialInformationbyDate(Date searchDate) {	
		
		List<PatientSerialDTO> resultList = new ArrayList<PatientSerialDTO>();
		
		List<PatientSerials> list=patientSerialRepository.findBySerialDate(searchDate);		
		
		int counter=1;
		
		for (PatientSerials item: list)
		{
			PatientSerialDTO dto =new PatientSerialDTO();
			dto.setItem_no(counter);
			dto.setPatientName(item.getPatientProfile().getName());
			dto.setMobile(item.getPatientProfile().getMobile());
			dto.setSerialDate(item.getSerialDate());
			dto.setSerialNumber(item.getSerialNumber());	
			dto.setRemarks(item.getRemarks());
			resultList.add(dto);
			counter++;
		}		
		
		return resultList;
	}
}
