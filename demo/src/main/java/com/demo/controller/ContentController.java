package com.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.commands.ContentForm;
import com.demo.commands.ContentSearchForm;
import com.demo.converter.DrugDataToDrugForm;
import com.demo.converter.DrugFormToDrugData;
import com.demo.repositories.ContentRepository;
import com.demo.repositories.DrugGenericRepository;
import com.demo.repositories.DrugManufacturerRepository;
import com.demo.services.DrugService;

@Controller
public class ContentController {

	private final Logger slf4jLogger = LoggerFactory.getLogger(ContentController.class);

	private DrugService drugService;
	private ContentRepository contentRepository;
	private DrugFormToDrugData drugFormToDrugData;
	private DrugManufacturerRepository drugManufacturerDaoServie;
	private DrugGenericRepository drugGenericDaoService;
	private DrugDataToDrugForm drugDataToDrugForm;

	@Autowired
	public void setservices(ContentRepository contentRepository, DrugFormToDrugData drugFormToDrugData,
			DrugManufacturerRepository drugManufacturerDaoServiec, DrugGenericRepository drugGenericDaoService,
			DrugDataToDrugForm drugDataToDrugForm) {

		this.contentRepository = contentRepository;
		this.drugFormToDrugData = drugFormToDrugData;
		this.drugManufacturerDaoServie = drugManufacturerDaoServiec;
		this.drugGenericDaoService = drugGenericDaoService;
		this.drugDataToDrugForm = drugDataToDrugForm;

	}

	@RequestMapping(value = "/contentList", method = RequestMethod.GET)
	public String list(Model model) {

		model.addAttribute("content", new ContentSearchForm());
		model.addAttribute("contents", contentRepository.findTop50ByOrderByInsertDateDesc());
		return "contents/contents";

	}
	
	@RequestMapping("content/new")
	public String newDrug(Model model) {
		
		ContentForm form = new ContentForm();			
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		dateFormat.format(today);
		form.setExpireDate(today);
		model.addAttribute("content", form);		
		
		return "contents/contentForm";
	}
	
	
	/*

	@RequestMapping(value = "/drugSearch", method = RequestMethod.POST)
	public String drugSearch(DrugSearchForm form, BindingResult bindingResult, Model model) {

		if (form.getDrugName() == null && form.getManufacturerId() == 0 && form.getGenericName()== null) {
			return "redirect:/drugList";

		}

		//DrugGeneric generic = null;
		DrugManufacturer brand2 = null;
		
		Integer brandId =null;
		//Integer genericId= null;
		
		
		
		if(form.getManufacturerId()!=0)
		{
			brand2 = new DrugManufacturer();
			brand2.setManufacturerId(form.getManufacturerId());
			brandId = form.getManufacturerId();
			model.addAttribute("drugBrand", brand2);
			
		}

		

		model.addAttribute("drug", form);
		model.addAttribute("brands", drugManufacturerDaoServie.findAll());
		//model.addAttribute("generics", drugGenericDaoService.findAll());

		
        List<Drug> drugsSearchResult =drugDaoService
				.findDrugByDrugManufacturerOrByDrugGenericOrDrugName(brandId,form.getGenericName(), form.getDrugName());

		
		model.addAttribute("drugs",drugsSearchResult );
		return "drugs/drugs";
	}



	@RequestMapping(value = "drug", method = RequestMethod.POST)
	public String saveDrug(@Valid @ModelAttribute("drug") DrugForm form, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("generics", drugGenericDaoService.findAll());
			model.addAttribute("brands", drugManufacturerDaoServie.findAll());
			model.addAttribute("drug", form);

			return "drugs/drugForm";
		}

		Drug drug = drugFormToDrugData.convert(form);

		drugDaoService.save(drug);

		return "redirect:/drugList";

	}

	@RequestMapping("drug/{id}")
	public String showDrug(@PathVariable Integer id, Model model) {

		slf4jLogger.info("DrugController :: showDrug");

		DrugForm form = drugDataToDrugForm.convert(drugDaoService.findById(id));
		model.addAttribute("drug", form);
		return "drugs/drugshow";
	}

	@RequestMapping("drug/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {

		DrugForm form = drugDataToDrugForm.convert(drugDaoService.findById(id));
		model.addAttribute("generics", drugGenericDaoService.findAll());
		model.addAttribute("brands", drugManufacturerDaoServie.findAll());

		model.addAttribute("drug", form);

		DrugGeneric generic = new DrugGeneric();
		generic.setGenericName(form.getGenericName());
		generic.setIdGeneric(form.getGenericId());
		model.addAttribute("drugGeneric", generic);

		DrugManufacturer brand2 = new DrugManufacturer();
		brand2.setManufacturer(form.getManufacturer());
		brand2.setManufacturerId(form.getManufacturerId());
		model.addAttribute("drugBrand", brand2);

		return "drugs/drugForm";

	}

	@RequestMapping("drug/delete/{id}")
	public String delete(@PathVariable Integer id) {
		try {

			drugDaoService.delete(id);
			return "redirect:/drugList";

		} catch (Exception ex) {

			return "redirect:/drugList";

		}
	}
	
	*/

}
