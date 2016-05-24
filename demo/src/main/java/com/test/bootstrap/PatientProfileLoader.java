package com.test.bootstrap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.test.domain.PatientProfile;
import com.test.domain.PatientSerials;
import com.test.domain.Product;
import com.test.repositories.PatientRepository;
import com.test.repositories.PatientSerialRepository;
import com.test.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class PatientProfileLoader implements ApplicationListener<ContextRefreshedEvent> {

	
    private PatientRepository patientRepository;
    
    private PatientSerialRepository patientSerialRepository;

    private Logger log = Logger.getLogger(PatientProfileLoader.class);

    @Autowired
    public void setProductRepository(PatientRepository productRepository, PatientSerialRepository patientSerialRepository) {
        this.patientRepository = productRepository;
        this.patientSerialRepository=patientSerialRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    	PatientProfile patientOne = new PatientProfile();
        patientOne.setName("Nahid");
        patientOne.setAge(35);
        patientOne.setMobile("01733400896");
        
        patientRepository.save(patientOne);
        
        PatientSerials oneSerial = new PatientSerials();
        oneSerial.setLastInsartedDate(new Date());        
        oneSerial.setRemarks("test");
        oneSerial.setSerialNumber(1);
        oneSerial.setPatientProfile(patientOne);
        oneSerial.setSerialDate(new Date());
        
        patientSerialRepository.save(oneSerial);
       
      
        

        log.info("Saved patient - id: " + patientOne.getId());

    	PatientProfile patientTwo = new PatientProfile();
        patientTwo.setName("Karim");
        patientTwo.setAge(3);
        patientTwo.setMobile("01733407896");
        
        patientRepository.save(patientTwo);
        
        
        PatientSerials twoSerial = new PatientSerials();
        twoSerial.setLastInsartedDate(new Date());
        twoSerial.setRemarks("test2");
        twoSerial.setPatientProfile(patientTwo);  
        twoSerial.setSerialNumber(2);
        twoSerial.setSerialDate(new Date());
        patientSerialRepository.save(twoSerial);  


        log.info("Saved patient - id:" + patientTwo.getId());
    }
}
