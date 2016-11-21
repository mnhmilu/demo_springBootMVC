package com.test.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.h2.jdbc.JdbcSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.domain.PatientProfile;
import com.test.domain.PatientSerials;
import com.test.dto.PatientSerialDTO;
import com.test.dto.PatientSerialSearchDTO;
import com.test.repositories.PatientRepository;
import com.test.repositories.PatientSerialRepository;
import com.test.services.PatientProfiletService;

@Controller
public class PatientController {
	
	private final Logger slf4jLogger = LoggerFactory.getLogger(PatientController.class);
	

	private PatientProfiletService patientProfiletService;
	private PatientRepository patientRepository;
	private PatientSerialRepository patientSerialRepository;

	@Autowired
	public void setpatientservice(PatientProfiletService patientProfiletService, PatientRepository patientRepository,
			PatientSerialRepository patientSerialRepository) {		
		
		this.patientProfiletService = patientProfiletService;
		this.patientRepository = patientRepository;
		this.patientSerialRepository = patientSerialRepository;
	}

	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public String list(Model model) {

		model.addAttribute("patient", new PatientProfile());
		model.addAttribute("patients", patientRepository.findTop50ByOrderByLastInsartedDateDesc());
		return "patients";
	}

	@RequestMapping("patient/{id}")
	public String showpatient(@PathVariable Integer id, Model model) {
		
		slf4jLogger.info("PatientController :: showpatient");
		
        PatientProfile patient = patientRepository.findById(id);	
		PatientSerialSearchDTO dto = new PatientSerialSearchDTO();
		dto.setId(patient.getId());
		dto.setAge(patient.getAge());
		dto.setMobile(patient.getMobile());	
		dto.setName(patient.getName());
		
		model.addAttribute("patient", dto);
		//git test
		//git test for branch

		return "patientshow";
	}

	@RequestMapping("patient/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		PatientProfile patient = patientRepository.findById(id);
		
		PatientSerialSearchDTO dto = new PatientSerialSearchDTO();
		dto.setId(patient.getId());
		dto.setAge(patient.getAge());
		dto.setMobile(patient.getMobile());		
		dto.setName(patient.getName());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		dateFormat.format(today);
		dto.setSerialDate(today);
		
		
		model.addAttribute("patient",dto );
		return "patientform";
	}

	@RequestMapping("patient/new")
	public String newpatient(Model model) {
		
		PatientSerialDTO dto= new PatientSerialDTO();		
		setDefaultDateInPatientProfile(dto);
		model.addAttribute("patient", dto);	
		return "patientform";
	}
	
	@RequestMapping(value = "patient", method = RequestMethod.POST)
	public String savepatient(@Valid @ModelAttribute("patient")  PatientSerialDTO patient, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {			
			return "patientform";
		}

		patientProfiletService.savePatientInfoWithSerail(patient);
		return "redirect:/patients";

	}


	@RequestMapping("patient/search")
	public String searchpatient(Model model) {
		model.addAttribute("patient", new PatientProfile());
		return "patientSearch";
	}

	@RequestMapping(value = "patientsearchResult", method = RequestMethod.POST)
	public String patientsearchResult(PatientProfile patient, Model model) {
		model.addAttribute("patient", new PatientProfile());
		model.addAttribute("patients",
				patientRepository.findByMobileOrNameIgnoreCase(patient.getMobile(), patient.getName()));
		return "patients";
	}

	
	@RequestMapping("patient/delete/{id}")
	public String delete(@PathVariable Integer id) {

		try {
			patientRepository.delete(id);
			return "redirect:/patients";
		} catch (Exception ex) {

			return "redirect:/patients";

		}
	}

	@RequestMapping(value = "patient/serials/{id}", method = RequestMethod.GET)
	public String seriallist(@PathVariable Integer id, Model model) {

		PatientProfile patient = patientRepository.findById(id);
		model.addAttribute("patient", patient);
		model.addAttribute("serials", patientSerialRepository.findByPatientProfile(patient));
		PatientSerialDTO dto = new PatientSerialDTO();
		dto.setPatientProfileId(patient.getId());
		setDefaultDateInPatientProfile(dto);
		model.addAttribute("serial", dto);

		return "patientSerialForm";
	}

	@RequestMapping(value = "serialEntry", method = RequestMethod.POST)
	public String savepatientSerial(@Valid @ModelAttribute("serial") PatientSerialDTO serial,  BindingResult bindingResult,
			RedirectAttributes redirectAttributes,Model model) {

		if (bindingResult.hasErrors()) {
			PatientProfile patient = patientRepository.findById(serial.getPatientProfileId());			
			model.addAttribute("patient", patient);			
			setDefaultDateInPatientProfile(serial);
			model.addAttribute("serial", serial);
			return "patientSerialForm";
		}

		PatientSerials patientSerial = setNewPatientProfile(serial);
		patientProfiletService.savePatientSerial(patientSerial);

		return "redirect:patient/serials/" + serial.getPatientProfileId();

	}

	
	
	@RequestMapping(value = "patient/serialSearchIndex", method = RequestMethod.GET)
	public String serialSearch( Model model) {		

		PatientSerialSearchDTO dto = new PatientSerialSearchDTO();		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		dateFormat.format(today);
		dto.setSerialDate(today);
		model.addAttribute("serial", dto);				
		List<PatientSerialDTO> resultList = new ArrayList<PatientSerialDTO>();		
		model.addAttribute("serials",resultList);		

		return "patientSerialSearchResult";
	}
	
	@RequestMapping(value = "patient/serialSearchResults", method = RequestMethod.POST)
	public String serialSearchResults(@Valid @ModelAttribute("serial") PatientSerialSearchDTO serial, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date today = new Date();
			dateFormat.format(today);
			serial.setSerialDate(today);
			model.addAttribute("serial", serial);		
			return "patientSerialSearchResult";
		}
		
		List<PatientSerialDTO> resultList = new ArrayList<PatientSerialDTO>();	
		resultList=patientProfiletService.searchPatientSerialInformationbyDate(serial.getSerialDate());
		model.addAttribute("serials",resultList);		

		return "patientSerialSearchResult";

	}
	
	private void setDefaultDateInPatientProfile(PatientSerialDTO serial) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		dateFormat.format(today);
		serial.setSerialDate(today);
	}

	private PatientSerials setNewPatientProfile(PatientSerialDTO serial) {
		PatientProfile patientt = new PatientProfile();
		patientt.setId(serial.getPatientProfileId());
		PatientSerials patientSerial = new PatientSerials();
		patientSerial.setPatientProfile(patientt);
		patientSerial.setSerialDate(serial.getSerialDate());
		patientSerial.setRemarks(serial.getRemarks());
		patientSerial.setLastInsartedDate(new Date());
		return patientSerial;
	}
	
	

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		// The date format to parse or output your dates
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		// Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		// Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}

}
