package com.demo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.commands.DrugForm;
import com.demo.commands.DrugGenericForm;
import com.demo.commands.DrugGenericSearchForm;
import com.demo.commands.DrugSearchForm;
import com.demo.converter.DrugGenericDataToDrugGenericForm;
import com.demo.converter.DrugGenericFormToDrugGenericData;
import com.demo.domain.Drug;
import com.demo.domain.DrugGeneric;
import com.demo.domain.DrugManufacturer;
import com.demo.repositories.DrugGenericRepository;
import com.demo.repositories.DrugManufacturerRepository;
import com.demo.repositories.DrugRepository;

@Controller
public class DrugGenericController {

	private final Logger slf4jLogger = LoggerFactory.getLogger(DrugGenericController.class);

	private DrugGenericRepository drugGenericDaoService;
	
	@Autowired
	private DrugGenericDataToDrugGenericForm drugGenericDataToDrugGenericForm;
	
	@Autowired
	private DrugGenericFormToDrugGenericData drugGenericFormToDrugGenericData;
	
	@Autowired
	private DrugRepository drugDaoService;
	
	@Autowired
	private DrugManufacturerRepository drugManufacturerDaoServie;
	
	
	

	@Autowired
	public void setservices(DrugGenericRepository drugGenericDaoService) {

		this.drugGenericDaoService = drugGenericDaoService;

	}

	@RequestMapping(value = "admin/drugGenericList", method = RequestMethod.GET)
	public String listGeneric(Model model,@PageableDefault(value = 10) Pageable pageable) {

		model.addAttribute("drugGeneric", new DrugGenericSearchForm());

		Page<DrugGeneric> productPage = drugGenericDaoService.findAll(pageable);
		PageWrapper<DrugGeneric> page = new PageWrapper<DrugGeneric>(productPage, "/admin/drugGenericList");
		model.addAttribute("drugGenerics", page.getContent());
		model.addAttribute("page", page);

		return "drugGeneric/drugsGenerics";

	}
	
	@RequestMapping(value = "/generic/brands/{key}", method = RequestMethod.GET)
	public String druglist(Model model, @PathVariable String key) {			

		List<Drug> drugsSearchResult = drugDaoService.findTop5ByDrugGeneric(Integer.parseInt(key));		
		DrugGeneric generic = drugGenericDaoService.findByIdGeneric(Integer.parseInt(key));
		
		model.addAttribute("generic", generic);	
		model.addAttribute("drugs", drugsSearchResult);	
		
		DrugForm form = new DrugForm();	
		model.addAttribute("brands", drugManufacturerDaoServie.findAll());
		model.addAttribute("drugBrand", new DrugManufacturer());
		model.addAttribute("drugGeneric", new DrugGeneric());
		form.setGenericId(Integer.valueOf(key));
		model.addAttribute("drug", form);		
		
		model.addAttribute("drugGeneric", generic);
		
		return "drugGeneric/drugs";

	}
	


	@RequestMapping(value = "admin/drugGenericSearch", method = RequestMethod.GET)
	public String drugGenericSearch(DrugGenericSearchForm form, BindingResult bindingResult, Model model,@PageableDefault(value = 10)
			Pageable pageable, HttpSession session) {

		if (form.getGenericName() == null && pageable.getPageNumber()==0) {
			return "redirect:/admin/drugGenericList";
		}	
		
		String searhKey = null;

		if (pageable.getPageNumber() == 0) {

			searhKey = form.getGenericName() == "" ? null : form.getGenericName();
			session.setAttribute("searhKey", searhKey);

		}

		else {

			if (session.getAttribute("searhKey") != null) {
				searhKey = (String) session.getAttribute("searhKey");
			}

		}
		

		model.addAttribute("drugGeneric", form);

		Page<DrugGeneric> productPage = drugGenericDaoService
				.findDrugGenericByGenericNameIgnoreCase(form.getGenericName(), pageable);
		PageWrapper<DrugGeneric> page = new PageWrapper<DrugGeneric>(productPage, "/admin/drugGenericSearch");
		model.addAttribute("drugGenerics", page.getContent());
		model.addAttribute("page", page);

		
		return "drugGeneric/drugsGenerics";
	}

	@RequestMapping("admin/generic/new")
	public String newDrugGeneric(Model model) {
		DrugGenericForm form = new DrugGenericForm();
		model.addAttribute("drugGeneric", form);
		return "drugGeneric/drugGenericForm";
	}

	@RequestMapping(value = "admin/generics", method = RequestMethod.POST)
	public String saveDrugGeneric(@Valid @ModelAttribute("drug") DrugGenericForm form, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("drugGeneric", form);
			return "drugGeneric/drugGenericForm";
		}

		DrugGeneric generic = drugGenericFormToDrugGenericData.convert(form);	
		generic.setInsertDate(new Date());
		drugGenericDaoService.save(generic);

		return "redirect:/admin/drugGenericList";

	}

	@RequestMapping("admin/generic/edit/{idGeneric}")
	public String editGeneric(@PathVariable Integer idGeneric, Model model) {
		
		DrugGenericForm form = new DrugGenericForm();
		DrugGeneric generic = drugGenericDaoService.findByIdGeneric(idGeneric);		
		form=drugGenericDataToDrugGenericForm.convert(generic);	
		model.addAttribute("drugGeneric", form);
		return "drugGeneric/drugGenericForm";

	}
	
	
	@RequestMapping("drugGenericDetails/{id}")
	public String showDrugGenericDeatails(@PathVariable Integer id, Model model) {

		DrugGenericForm form = drugGenericDataToDrugGenericForm.convert(drugGenericDaoService.findByIdGeneric(id));
		model.addAttribute("generic", form);
		
		List<Drug> drugsSearchResult = drugDaoService.findTop5ByDrugGeneric(id);	
		model.addAttribute("drugs", drugsSearchResult);	
		
		
		return "drugGeneric/drugGenericDetails";
	}

	@RequestMapping("admin/generic/delete/{id}")
	public String deleteGeneric(@PathVariable Integer id) {
		try {

			drugGenericDaoService.delete(id);
			return "redirect:/admin/drugGenericList";

		} catch (Exception ex) {

			return "redirect:/admin/drugGenericList";

		}
	}
	
	


}
