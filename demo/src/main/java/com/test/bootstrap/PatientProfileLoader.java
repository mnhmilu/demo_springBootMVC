package com.test.bootstrap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.test.domain.PatientProfile;
import com.test.domain.Product;
import com.test.repositories.PatientRepository;
import com.test.repositories.ProductRepository;

import java.math.BigDecimal;

@Component
public class PatientProfileLoader implements ApplicationListener<ContextRefreshedEvent> {

	
    private PatientRepository patientRepository;

    private Logger log = Logger.getLogger(PatientProfileLoader.class);

    @Autowired
    public void setProductRepository(PatientRepository productRepository) {
        this.patientRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    	PatientProfile patientOne = new PatientProfile();
        patientOne.setName("Nahid");
        patientOne.setAge(35);
        patientOne.setMobile("01733400896");
        
        patientRepository.save(patientOne);

        log.info("Saved patient - id: " + patientOne.getId());

    	PatientProfile patientTwo = new PatientProfile();
        patientTwo.setName("Karim");
        patientTwo.setAge(345);
        patientTwo.setMobile("01733407896");
        
        patientRepository.save(patientTwo);


        log.info("Saved patient - id:" + patientTwo.getId());
    }
}
