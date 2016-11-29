package com.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.commands.DrugForm;
import com.demo.commands.DrugSearchForm;
import com.demo.converter.DrugDataToDrugForm;
import com.demo.domain.Drug;
import com.demo.repositories.DrugRepository;

import scala.annotation.meta.getter;

@Controller
public class IndexController {

	private DrugRepository drugDaoService;
	private DrugDataToDrugForm drugDataToDrugForm;
	
	private final Logger slf4jLogger = LoggerFactory.getLogger(DrugController.class);
	

	@Autowired
	public void setservices(DrugRepository drugDaoService,DrugDataToDrugForm drugDataToDrugForm) {

		this.drugDaoService = drugDaoService;
		this.drugDataToDrugForm = drugDataToDrugForm;

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("drug", new DrugSearchForm());
		return "index";

	}

	@RequestMapping(value = "/drugSearchFromIndex", method = RequestMethod.POST)
	public String drugSearch(DrugSearchForm form, BindingResult bindingResult, Model model) {

		if (form.getDrugName() == null || form.getDrugName().trim()=="") {
			return "redirect:/";

		}

		List<Drug> drugsSearchResult = drugDaoService.findDrugByDrugManufacturerOrByDrugGenericOrDrugName(null, "",
				form.getDrugName());

		model.addAttribute("drugs", drugsSearchResult);
		return "drugs/drugsGenericSearch";

	}
	
	@RequestMapping("/index/drugDeatails/{id}")
	public String showDrug(@PathVariable Integer id, Model model) {

		slf4jLogger.info("DrugController :: showDrug");

		DrugForm form = drugDataToDrugForm.convert(drugDaoService.findById(id));
		model.addAttribute("drug", form);
		return "drugs/drugDetailsGeneral";
	}
	
	@RequestMapping("/index/drugByGeneric/{key}")
	public String showDrugByGeneric(@PathVariable String key, Model model) {

		slf4jLogger.info("DrugController :: showDrugByGeneric");
		List<Drug> drugsSearchResult =drugDaoService.findDrugByDrugGeneric(key);
		model.addAttribute("drugs", drugsSearchResult);
		return "drugs/drugsGenericSearch";
	}
	
	
	
	
	
	

}
