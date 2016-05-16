package  com.test.services;

import com.test.domain.PatientProfile;
import com.test.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientProfiletService {
	
	
    private PatientRepository patientRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
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
}
