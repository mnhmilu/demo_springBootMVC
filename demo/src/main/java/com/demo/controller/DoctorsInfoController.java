package com.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
	public void setservices(DoctorsInfoRepository doctorsInfoRepository,
			DoctorsSpecializaitonRepository doctorsSpecializaitonRepository) {

		this.doctorsInfoRepository = doctorsInfoRepository;
		this.doctorsSpecializaitonRepository = doctorsSpecializaitonRepository;

	}

	@RequestMapping(value = "/specializations", method = RequestMethod.GET)
	public String spcializationlist(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		slf4jLogger.info("DoctorsInfoController ::spcializationlist:: Doctor page accessed by :" + name);

		model.addAttribute("specializations", doctorsSpecializaitonRepository.findAll());

		model.addAttribute("doctorCount", doctorsInfoRepository.count());

		return "doctors/specializations";

	}

	@RequestMapping(value = "/doctorsList/{idSpecialization}/{specializationName}", method = RequestMethod.GET)
	public String doctorlist(Model model, @PathVariable Integer idSpecialization,
			@PathVariable String specializationName, Pageable pageable) {

		Page<DoctorsInfo> SearchResultPage = doctorsInfoRepository
				.findDoctorsInfoByDoctorsSpecialization(idSpecialization, pageable);
		PageWrapper<DoctorsInfo> page = new PageWrapper<DoctorsInfo>(SearchResultPage,
				"/doctorsList/" + idSpecialization + "/" + specializationName);
		model.addAttribute("doctors", SearchResultPage);
		model.addAttribute("page", page);

		model.addAttribute("specialization", specializationName);

		return "doctors/doctors";

	}

	@RequestMapping("doctor/new")
	public String newDrug(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		DoctorForm form = new DoctorForm();
		form.setInsertDate(new Date());
		form.setInsertedBy(name);
		model.addAttribute("specializations", doctorsSpecializaitonRepository.findAll());
		model.addAttribute("doctorsSpecialization", new DoctorsSpecialization());
		model.addAttribute("doctor", form);
		return "doctors/doctorForm";
	}

	@RequestMapping(value = "doctor/SaveDoctor", method = RequestMethod.POST)
	public String saveDoctor(@Valid @ModelAttribute("doctor") DoctorForm form, BindingResult bindingResult,
			Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		if (bindingResult.hasErrors()) {

			model.addAttribute("specializations", doctorsSpecializaitonRepository.findAll());
			model.addAttribute("doctor", form);
			return "doctors/doctorForm";
		}

		DoctorsInfo entity = new DoctorsInfo();
		entity.setChamber(form.getChamber());
		entity.setDoctorDetails(form.getDoctorDetails());
		entity.setDoctorName(form.getDoctorName());
		entity.setId(form.getId());

		if (form.getId() == null) {
			entity.setInsertedBy(name);
			entity.setInsertDate(new Date());
		} else {
			entity.setUpdatedBy(name);
			entity.setUpdateDate(new Date());
			entity.setInsertDate(form.getInsertDate());
			entity.setInsertedBy(form.getInsertedBy());
			
		}

		DoctorsSpecialization speciality = new DoctorsSpecialization();
		speciality.setIdSpecialization(form.getIdSpecialization());
		entity.setDoctorsSpecialization(speciality);

		doctorsInfoRepository.save(entity);

		if (form.getId() == null) {
			return "redirect:/doctor/new";
		} else {
			return "redirect:/doctorsList/" + form.getIdSpecialization() + "/" + doctorsSpecializaitonRepository
					.findByIdSpecialization(form.getIdSpecialization()).getSpecializationName();
		}

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
		form.setInsertDate(entity.getInsertDate());
		form.setInsertedBy(entity.getInsertedBy());

		model.addAttribute("specializations", doctorsSpecializaitonRepository.findAll());

		model.addAttribute("doctor", form);

		DoctorsSpecialization speciality = new DoctorsSpecialization();
		speciality.setIdSpecialization(form.getIdSpecialization());
		entity.setDoctorsSpecialization(speciality);

		model.addAttribute("doctorsSpecialization", speciality);

		return "doctors/doctorForm";

	}

	@RequestMapping("doctor/delete/{id}")
	public String delete(@PathVariable Integer id) {
		DoctorsInfo entity = doctorsInfoRepository.findById(id);
		try {

			doctorsInfoRepository.delete(id);

			return "redirect:/doctorsList/" + entity.getDoctorsSpecialization().getIdSpecialization() + "/"
					+ entity.getDoctorsSpecialization().getSpecializationName();

		} catch (Exception ex) {

			return "redirect:/doctorsList/" + entity.getDoctorsSpecialization().getIdSpecialization() + "/"
					+ entity.getDoctorsSpecialization().getSpecializationName();

		}
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
