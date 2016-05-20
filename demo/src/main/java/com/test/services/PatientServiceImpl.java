package  com.test.services;

import com.test.domain.PatientProfile;
import com.test.domain.PatientSerials;
import com.test.repositories.PatientRepository;
import com.test.repositories.PatientSerialRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientProfiletService {
	
	
    private PatientRepository patientRepository;
    private PatientSerialRepository patientSerialRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository,PatientSerialRepository patientSerialRepository) {
        this.patientRepository = patientRepository;
        this.patientSerialRepository=patientSerialRepository;
    }

    @Override
    public Iterable<PatientProfile> listAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public PatientProfile getPatientProfileById(Integer id) {
        return patientRepository.findOne(id);
    }

    @Override
    public PatientProfile savePatientProfile(PatientProfile item) {
        return patientRepository.save(item);
    }

    @Override
    public void deletePatientProfile(Integer id) {
    	patientRepository.delete(id);
    }

	@Override
	public Iterable<PatientProfile> getPatientProfilebySearch(String mobile) {
		
		return patientRepository.getPatientProfilebySearch("%"+mobile+"%");
	}

	@Override
	public void savePatientSerial(PatientSerials patientSerial) {
		
		
		List<PatientSerials> list=patientSerialRepository.findBySerialDate(patientSerial.getSerialDate());
		
		patientSerial.setSerialNumber(list.size()+1);		
		
		patientSerialRepository.save(patientSerial);	
		
		
	}
}
