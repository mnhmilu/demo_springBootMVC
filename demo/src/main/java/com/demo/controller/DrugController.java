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
import com.demo.commands.DrugSearchForm;
import com.demo.converter.DrugDataToDrugForm;
import com.demo.converter.DrugFormToDrugData;
import com.demo.domain.Drug;
import com.demo.domain.DrugBrand;
import com.demo.domain.DrugGeneric;
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
	public void setservices(DrugRepository drugDaoService, DrugFormToDrugData drugFormToDrugData,
			DrugBrandRepository drugBrandDaoServiec, DrugGenericRepository drugGenericDaoService,
			DrugDataToDrugForm drugDataToDrugForm) {

		this.drugDaoService = drugDaoService;
		this.drugFormToDrugData = drugFormToDrugData;
		this.drugBrandDaoServiec = drugBrandDaoServiec;
		this.drugGenericDaoService = drugGenericDaoService;
		this.drugDataToDrugForm = drugDataToDrugForm;

	}

	@RequestMapping(value = "/drugList", method = RequestMethod.GET)
	public String list(Model model) {

		model.addAttribute("drug", new DrugSearchForm());
		model.addAttribute("drugs", drugDaoService.findTop50ByOrderByInsertDateDesc());
		model.addAttribute("brands", drugBrandDaoServiec.findAll());
		model.addAttribute("generics", drugGenericDaoService.findAll());

		return "drugs/drugs";

	}

	@RequestMapping(value = "/drugSearch", method = RequestMethod.POST)
	public String drugSearch(DrugSearchForm form,BindingResult bindingResult, Model model) {

		if (form.getDrugName() == null && form.getBrandId() == null && form.getGenericId() == null) {
			return "redirect:/drugList";

		}

		DrugGeneric generic = new DrugGeneric();

		DrugBrand brand2 = new DrugBrand();

		model.addAttribute("drug", form);
		model.addAttribute("brands", drugBrandDaoServiec.findAll());
		model.addAttribute("generics", drugGenericDaoService.findAll());

		if (form.getGenericId() != null) {

			generic.setId(form.getGenericId());
			model.addAttribute("drugGeneric", generic);
		}
		if (form.getBrandId() != null) {

			brand2.setId(form.getBrandId());
			model.addAttribute("drugBrand", brand2);
		}

		model.addAttribute("drugs",
				drugDaoService.findDrugByDrugGenericOrDrugBrandOrDrugNameContainingIgnoreCase(generic, brand2, form.getDrugName()));
		return "drugs/drugs";
	}

	@RequestMapping("drug/new")
	public String newDrug(Model model) {
		DrugForm form = new DrugForm();
		model.addAttribute("generics", drugGenericDaoService.findAll());
		model.addAttribute("brands", drugBrandDaoServiec.findAll());
		model.addAttribute("drugBrand", new DrugBrand());
		model.addAttribute("drugGeneric", new DrugGeneric());
		model.addAttribute("drug", form);
		return "drugs/drugForm";
	}

	@RequestMapping(value = "drug", method = RequestMethod.POST)
	public String saveDrug(@Valid @ModelAttribute("drug") DrugForm form, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
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
		model.addAttribute("brands", drugBrandDaoServiec.findAll());

		model.addAttribute("drug", form);

		DrugGeneric generic = new DrugGeneric();
		generic.setGenericName(form.getGenericName());
		generic.setId(form.getGenericId());
		model.addAttribute("drugGeneric", generic);

		DrugBrand brand2 = new DrugBrand();
		brand2.setBrandName(form.getBrandName());
		brand2.setId(form.getBrandId());
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

}
