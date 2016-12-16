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

import com.demo.commands.DoctorForm;
import com.demo.domain.DoctorsInfo;
import com.demo.domain.DoctorsSpecialization;
import com.demo.repositories.DoctorsInfoRepository;
import com.demo.repositories.DoctorsSpecializaitonRepository;

@Controller
public class DoctorsInfoController {

	private final Logger slf4jLogger = LoggerFactory.getLogger(DoctorsInfoController.class);

	
	private DoctorsInfoRepository doctorsInfoRepository;
	
	private DoctorsSpecializaitonRepository doctorsSpecializaitonRepository;


	@Autowired
	public void setservices(DoctorsInfoRepository doctorsInfoRepository,DoctorsSpecializaitonRepository doctorsSpecializaitonRepository) {

		this.doctorsInfoRepository=doctorsInfoRepository;
		this.doctorsSpecializaitonRepository=doctorsSpecializaitonRepository;

	}
	
	@RequestMapping(value = "/specializations", method = RequestMethod.GET)
	public String spcializationlist(Model model) {
		
		model.addAttribute("specializations", doctorsSpecializaitonRepository.findAll());	
		return "doctors/specializations";

	}	

	@RequestMapping(value = "/doctorsList/{idSpecialization}", method = RequestMethod.GET)
	public String doctorlist(Model model,@PathVariable Integer idSpecialization) {

		model.addAttribute("doctors", doctorsInfoRepository.findTop50ByOrderByInsertDateDesc());		
		return "doctors/doctors";

	}	

	@RequestMapping("doctor/new")
	public String newDrug(Model model) {
		DoctorForm form = new DoctorForm();
		model.addAttribute("specializations", doctorsSpecializaitonRepository.findAll());	
		model.addAttribute("doctorsSpecialization",new DoctorsSpecialization());
		model.addAttribute("doctor", form);
		return "doctors/doctorForm";
	}

	@RequestMapping(value = "doctor", method = RequestMethod.POST)
	public String saveDrug(@Valid @ModelAttribute("doctor") DoctorForm form, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			
			model.addAttribute("specializations", doctorsSpecializaitonRepository.findAll());	
			model.addAttribute("doctor", form);
			return "doctors/doctorForm";
		}		
		
		DoctorsInfo entity = new DoctorsInfo();
		entity.setChamber(form.getChamber());
		entity.setDoctorDetails(form.getDoctorDetails());
		entity.setDoctorName(form.getDoctorName());
		
		DoctorsSpecialization speciality = new DoctorsSpecialization();
		speciality.setIdSpecialization(form.getIdSpecialization());		
		entity.setDoctorsSpecialization(speciality);		

		doctorsInfoRepository.save(entity);

		return "redirect:/doctorsList";

	}

	@RequestMapping("doctor/{id}")
	public String showDrug(@PathVariable Integer id, Model model) {

		slf4jLogger.info("DoctorController :: showDrug");		
		
		DoctorsInfo entity = doctorsInfoRepository.findById(id);		
		DoctorForm form = new DoctorForm();
		form.setChamber(entity.getChamber());
		form.setDoctorDetails(entity.getDoctorDetails());
		form.setDoctorName(entity.getDoctorName());
		form.setId(entity.getId());
		form.setIdSpecialization(entity.getDoctorsSpecialization().getIdSpecialization());
		form.setSpecializationName(entity.getDoctorsSpecialization().getSpecializationName());		
		
		model.addAttribute("doctor", form);
		return "doctors/doctorshow";
	}

	@RequestMapping("doctor/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		DoctorsInfo entity = doctorsInfoRepository.findById(id);
		DoctorForm form = new DoctorForm();
		form.setChamber(entity.getChamber());
		form.setDoctorDetails(entity.getDoctorDetails());
		form.setDoctorName(entity.getDoctorName());
		form.setId(entity.getId());
		form.setIdSpecialization(entity.getDoctorsSpecialization().getIdSpecialization());
		form.setSpecializationName(entity.getDoctorsSpecialization().getSpecializationName());		
		
		model.addAttribute("specializations", doctorsSpecializaitonRepository.findAll());			

		model.addAttribute("doctor", form);		
		
		DoctorsSpecialization speciality = new DoctorsSpecialization();
		speciality.setIdSpecialization(form.getIdSpecialization());		
		entity.setDoctorsSpecialization(speciality);	
		
		model.addAttribute("doctorsSpecialization",speciality);


		return "doctors/doctorForm";

	}

	@RequestMapping("doctor/delete/{id}")
	public String delete(@PathVariable Integer id) {
		try {

			doctorsInfoRepository.delete(id);
			return "redirect:/doctorsList";

		} catch (Exception ex) {

			return "redirect:/doctorsList";

		}
	}

}
