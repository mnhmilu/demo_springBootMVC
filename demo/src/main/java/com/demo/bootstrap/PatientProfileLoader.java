package com.demo.bootstrap;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.demo.domain.Drug;
import com.demo.domain.DrugBrand;
import com.demo.domain.DrugGeneric;
import com.demo.repositories.DrugBrandRepository;
import com.demo.repositories.DrugGenericRepository;
import com.demo.repositories.DrugRepository;

@Component
public class PatientProfileLoader implements ApplicationListener<ContextRefreshedEvent> {
    
    private DrugBrandRepository drugBrandDaoServiec;
    
    private DrugGenericRepository drugGenericRepository;
    
    private DrugRepository drugRepository;

    private Logger log = Logger.getLogger(PatientProfileLoader.class);

    @Autowired
    public void setProductRepository(DrugRepository drugRepository,DrugBrandRepository drugBrandDaoServiec,DrugGenericRepository drugGenericRepository) {
       
        this.drugRepository =drugRepository;
        this.drugBrandDaoServiec=drugBrandDaoServiec;
        this.drugGenericRepository=drugGenericRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    	/*
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
        
        */
        
        

        
        DrugBrand brand = new DrugBrand();
        brand.setBrandName("Brand A");
        drugBrandDaoServiec.save(brand);
        
        DrugBrand brand2 = new DrugBrand();
        brand2.setBrandName("Brand B");
        drugBrandDaoServiec.save(brand2);
        
        DrugGeneric generic = new DrugGeneric();
        generic.setGenericName("Generic X");
        drugGenericRepository.save(generic);
            
                
        
        
        
      Drug newDrug = new Drug();
      
      newDrug.setDrugBrand(brand);
      
      newDrug.setDrugGeneric(generic);
      newDrug.setDrugName("NAPA");
      newDrug.setInsertDate(new Date());      
      
     
      
      drugRepository.save(newDrug);      
      
      
        
        
        
        
    }
}
