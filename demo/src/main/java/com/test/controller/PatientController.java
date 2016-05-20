package com.test.controller;

import com.test.domain.PatientProfile;
import com.test.domain.PatientSerials;
import com.test.dto.PatientSerialDTO;
import com.test.repositories.PatientRepository;
import com.test.repositories.PatientSerialRepository;
import com.test.services.PatientProfiletService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PatientController {

    private PatientProfiletService patientProfiletService;
    private PatientRepository patientRepository; 
    private PatientSerialRepository patientSerialRepository;

    @Autowired
    public void setpatientservice(PatientProfiletService patientProfiletService,PatientRepository patientRepository,PatientSerialRepository patientSerialRepository) {
        this.patientProfiletService = patientProfiletService;
        this.patientRepository=patientRepository;
        this.patientSerialRepository=patientSerialRepository;
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
    	patient.setLastInsartedDate(new Date());
        patientProfiletService.savePatientProfile(patient);
        return "redirect:/patients";
      
    }

    @RequestMapping("patient/delete/{id}")
    public String delete(@PathVariable Integer id){
        patientProfiletService.deletePatientProfile(id);
        return "redirect:/patients";
    }
    
    @RequestMapping(value = "patient/serials/{id}", method = RequestMethod.GET)
    public String seriallist(@PathVariable Integer id,Model model){
    	
    	 PatientProfile patient=patientProfiletService.getPatientProfileById((id));    	
    	 model.addAttribute("patient", patient);    	 
    	  	 
    	 
    	 model.addAttribute("serials", patientSerialRepository.findByPatientProfile(patient));
    	 
    	 
    	 PatientSerialDTO dto = new PatientSerialDTO();
    	 dto.setPatientProfileId(patient.getId());  
    	 
    	 SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    	 Date today = new Date();
    	 
    	 dateFormat.format(today);
    	 dto.setSerialDate(today);
    	 	 
    	 model.addAttribute("serial", dto);
    	 
         return "patientSerialForm";
    }
    
    @RequestMapping(value = "serialEntry", method = RequestMethod.POST)
    public String savepatientSerial(@ModelAttribute("serial") PatientSerialDTO serial,BindingResult bindingResult,RedirectAttributes redirectAttributes){
    	
    	if(bindingResult.hasErrors())
    	{
    		 return "patientSerialForm";    		
    	}
    	
    	PatientProfile patient = new PatientProfile();
    	patient.setId(serial.getPatientProfileId());   	
    	PatientSerials patientSerial= new PatientSerials();
    	patientSerial.setPatientProfile(patient);    	
    	patientSerial.setSerialDate(serial.getSerialDate());
    	patientSerial.setRemarks(serial.getRemarks());    
    	patientSerial.setLastInsartedDate(new Date()); 	
    	patientProfiletService.savePatientSerial(patientSerial);
    	
    	return "redirect:patient/serials/"+serial.getPatientProfileId();
    	
      
      
    }
    
    @InitBinder
    private void dateBinder(WebDataBinder binder) {
                //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
                //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }

    
    
    


}
