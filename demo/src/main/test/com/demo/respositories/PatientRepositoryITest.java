package com.demo.respositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.configuration.RepositoryConfiguration;
import com.test.domain.PatientProfile;
import com.test.repositories.PatientRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class PatientRepositoryITest {
	
	private PatientRepository patientRepository;
	 
    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
 
    @Test
    public void testSavePatient(){
        
        //setup patient        
        PatientProfile patientOne = new PatientProfile();
        patientOne.setName("Nahid");
        patientOne.setAge(35);
        patientOne.setMobile("01733400896");             
 
        //save patient, verify has ID value after save
        assertNull(patientOne.getId()); //null before save
        patientRepository.save(patientOne);
        assertNotNull(patientOne.getId()); //not null after save
 
        //fetch from DB
        PatientProfile fetchedPatient = patientRepository.findOne(patientOne.getId());
 
        //should not be null
        assertNotNull(fetchedPatient);
 
        //should equal
        assertEquals(patientOne.getId(), fetchedPatient.getId());
        assertEquals(patientOne.getName(), fetchedPatient.getName());
 
        //update description and save
        fetchedPatient.setName("Karim");
        patientRepository.save(fetchedPatient);
 
        //get from DB, should be updated
        PatientProfile fetchedUpdatedProduct = patientRepository.findOne(fetchedPatient.getId());
        assertEquals(fetchedPatient.getName(), fetchedUpdatedProduct.getName());       
 
        //verify count of patients in DB
        long patientCount = patientRepository.count();
        assertEquals(patientCount, 1);
 
        //get all products, list should only have one
        Iterable<PatientProfile> patients = patientRepository.findAll();
 
        int count = 0;
 
        for(PatientProfile p : patients){
            count++;
        }
 
        assertEquals(count, 1);
    }
	
	

}
