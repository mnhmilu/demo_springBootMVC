package com.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.commands.DrugForm;
import com.demo.converter.DrugDataToDrugForm;
import com.demo.converter.DrugFormToDrugData;
import com.demo.repositories.DrugBrandRepository;
import com.demo.repositories.DrugGenericRepository;
import com.demo.repositories.DrugRepository;
import com.demo.services.DrugService;

@Controller
public class DrugController {
	
	private final Logger slf4jLogger = LoggerFactory.getLogger(DrugController.class);
	

	private DrugService drugService;
	private DrugRepository drugDaoService;
	private DrugFormToDrugData drugFormToDrugData;
	private DrugBrandRepository drugBrandDaoServiec;
	private DrugGenericRepository drugGenericDaoService;
	private DrugDataToDrugForm drugDataToDrugForm;
	
	
	

	@Autowired
	public void setservices( 
	 DrugRepository drugDaoService,
	 DrugFormToDrugData drugFormToDrugData,DrugBrandRepository drugBrandDaoServiec,DrugGenericRepository drugGenericDaoService, DrugDataToDrugForm drugDataToDrugForm) {
		
		this.drugDaoService = drugDaoService;
		this.drugFormToDrugData = drugFormToDrugData;
		this.drugBrandDaoServiec = drugBrandDaoServiec;
		this.drugGenericDaoService =drugGenericDaoService;
		this.drugDataToDrugForm=drugDataToDrugForm;
		
	}

	@RequestMapping(value = "/drugList", method = RequestMethod.GET)
	public String list(Model model) {

		model.addAttribute("drug", new DrugForm());
		model.addAttribute("drugs", drugDaoService.findTop50ByOrderByInsertDateDesc());
		model.addAttribute("brands",drugBrandDaoServiec.findAll());
		model.addAttribute("generics",drugGenericDaoService.findAll());
			
		return "drugs/drugs";
		
	}
	
	@RequestMapping("drug/new")
	public String newDrug(Model model) {
		DrugForm form = new DrugForm();
		model.addAttribute("generics",drugGenericDaoService.findAll());
		model.addAttribute("brands",drugBrandDaoServiec.findAll());
		model.addAttribute("drug", form);	
		return "drugs/drugForm";
	}
	
	@RequestMapping(value = "drug", method = RequestMethod.POST)
	public String saveDrug(@Valid @ModelAttribute("drug")  DrugForm form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {			
			return "drugs/drugForm";
		}
		
		drugDaoService.save(drugFormToDrugData.convert(form));

	//	drugService.savePatientInfoWithSerail(patientSerialFormToPatientData.convert( patientForm),patientForm.getSerialDate());
		return "redirect:/drugList";

	}

	
	@RequestMapping("drug/{id}")
	public String showDrug(@PathVariable Integer id, Model model) {
		
		slf4jLogger.info("DrugController :: showDrug");
		

		DrugForm form = drugDataToDrugForm.convert(drugDaoService.findById(id));
		
		model.addAttribute("drug", form);

		return "drugs/drugshow";
	}

	
	
	
	
	
	/*

	
	@RequestMapping("patient/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		PatientProfile patient = drugDaoService.findById(id);
		
		PatientSerialSearchForm dto = new PatientSerialSearchForm();
		dto.setId(id);
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


	
	@RequestMapping(value = "patient", method = RequestMethod.POST)
	public String savepatient(@Valid @ModelAttribute("patient")  PatientSerialForm patientForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {			
			return "patientform";
		}

		drugService.savePatientInfoWithSerail(patientSerialFormToPatientData.convert( patientForm),patientForm.getSerialDate());
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
				drugDaoService.findByMobileOrNameIgnoreCase(patient.getMobile(), patient.getName()));
		return "patients";
	}

	
	@RequestMapping("patient/delete/{id}")
	public String delete(@PathVariable Integer id) {

		try {
			drugDaoService.delete(id);
			return "redirect:/patients";
		} catch (Exception ex) {

			return "redirect:/patients";

		}
	}

	@RequestMapping(value = "patient/serials/{id}", method = RequestMethod.GET)
	public String seriallist(@PathVariable Integer id, Model model) {

		PatientProfile patient = drugDaoService.findById(id);
		model.addAttribute("patient", patient);
		model.addAttribute("serials", patientSerialRepository.findByPatientProfile(patient));
		PatientSerialForm dto = new PatientSerialForm();
		dto.setPatientProfileId(patient.getId());
		setDefaultDateInPatientProfile(dto);
		model.addAttribute("serial", dto);

		return "patientSerialForm";
	}

	@RequestMapping(value = "serialEntry", method = RequestMethod.POST)
	public String savepatientSerial(@Valid @ModelAttribute("serial") PatientSerialForm serial,  BindingResult bindingResult,
			RedirectAttributes redirectAttributes,Model model) {

		if (bindingResult.hasErrors()) {
			PatientProfile patient = drugDaoService.findById(serial.getPatientProfileId());			
			model.addAttribute("patient", patient);			
			setDefaultDateInPatientProfile(serial);
			model.addAttribute("serial", serial);
			return "patientSerialForm";
		}

		PatientSerials patientSerial = setNewPatientProfile(serial);
		drugService.savePatientSerial(patientSerial);

		return "redirect:patient/serials/" + serial.getPatientProfileId();

	}

	
	
	@RequestMapping(value = "patient/serialSearchIndex", method = RequestMethod.GET)
	public String serialSearch( Model model) {		

		PatientSerialSearchForm dto = new PatientSerialSearchForm();		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		dateFormat.format(today);
		dto.setSerialDate(today);
		model.addAttribute("serial", dto);				
		List<PatientSerialForm> resultList = new ArrayList<PatientSerialForm>();		
		model.addAttribute("serials",resultList);		

		return "patientSerialSearchResult";
	}
	
	@RequestMapping(value = "patient/serialSearchResults", method = RequestMethod.POST)
	public String serialSearchResults(@Valid @ModelAttribute("serial") PatientSerialSearchForm serial, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date today = new Date();
			dateFormat.format(today);
			serial.setSerialDate(today);
			model.addAttribute("serial", serial);		
			return "patientSerialSearchResult";
		}
		
		List<PatientSerialForm> resultList = new ArrayList<PatientSerialForm>();	
		resultList=drugService.searchPatientSerialInformationbyDate(serial.getSerialDate());
		model.addAttribute("serials",resultList);		

		return "patientSerialSearchResult";

	}
	
	private void setDefaultDateInPatientProfile(PatientSerialForm serial) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		dateFormat.format(today);
		serial.setSerialDate(today);
	}

	private PatientSerials setNewPatientProfile(PatientSerialForm serial) {
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
	
	*/

}
