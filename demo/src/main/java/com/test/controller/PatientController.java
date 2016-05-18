package com.test.controller;

import com.test.domain.PatientProfile;
import com.test.repositories.PatientRepository;
import com.test.services.PatientProfiletService;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PatientController {

    private PatientProfiletService patientProfiletService;
    private PatientRepository patientRepository; 

    @Autowired
    public void setpatientservice(PatientProfiletService patientProfiletService,PatientRepository patientRepository) {
        this.patientProfiletService = patientProfiletService;
        this.patientRepository=patientRepository;
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("patients", patientProfiletService.listAllPatients());     
        return "patients";
    }

    @RequestMapping("patient/{id}")
    public String showpatient(@PathVariable Integer id, Model model){
        model.addAttribute("patient", patientProfiletService.getPatientProfileById(id));        
       
        return "patientshow";
    }

    @RequestMapping("patient/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("patient", patientProfiletService.getPatientProfileById((id)));
        return "patientform";
    }

    @RequestMapping("patient/new")
    public String newpatient(Model model ){
        model.addAttribute("patient", new PatientProfile());
        return "patientform";
    }
    
    @RequestMapping("patient/search")
    public String searchpatient(Model model){
        model.addAttribute("patient", new PatientProfile());
        return "patientSearch";
    }
    @RequestMapping(value = "patientsearchResult", method = RequestMethod.POST)
    public String patientsearchResult(PatientProfile patient, Model model){    	
    	
    	 model.addAttribute("patients", patientRepository.findByMobileOrNameIgnoreCase(patient.getMobile(),patient.getName()));  
         return "patients";
      
    }
    

    @RequestMapping(value = "patient", method = RequestMethod.POST)
    public String savepatient(@Valid PatientProfile patient, BindingResult bindingResult){
    	
    	if(bindingResult.hasErrors())
    	{
    		 return "patientform";    		
    	}
        patientProfiletService.savePatientProfile(patient);
        return "redirect:/patients";
      
    }

    @RequestMapping("patient/delete/{id}")
    public String delete(@PathVariable Integer id){
        patientProfiletService.deletePatientProfile(id);
        return "redirect:/patients";
    }
    


}
