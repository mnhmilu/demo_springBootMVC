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
import com.demo.repositories.DrugGenericRepository;
import com.demo.repositories.DrugRepository;

import scala.annotation.meta.getter;

@Controller
public class IndexController {

	private DrugRepository drugDaoService;
	private DrugDataToDrugForm drugDataToDrugForm;
	private DrugGenericRepository drugGenericDaoService;
	
	private final Logger slf4jLogger = LoggerFactory.getLogger(DrugController.class);
	

	@Autowired
	public void setservices(DrugRepository drugDaoService,DrugDataToDrugForm drugDataToDrugForm,DrugGenericRepository drugGenericDaoService) {

		this.drugDaoService = drugDaoService;
		this.drugDataToDrugForm = drugDataToDrugForm;
		this.drugGenericDaoService=drugGenericDaoService;

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("drug", new DrugSearchForm());
		model.addAttribute("drugCount", drugDaoService.count());
		model.addAttribute("genericCount", drugGenericDaoService.count());
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

		slf4jLogger.info("IndexController :: showDrug");

		DrugForm form = drugDataToDrugForm.convert(drugDaoService.findById(id));
		model.addAttribute("drug", form);
		return "drugs/drugDetailsGeneral";
	}
	
	@RequestMapping("/index/drugByGeneric/{key}")
	public String showDrugByGeneric(@PathVariable String key, Model model) {

		slf4jLogger.info("IndexController :: showDrugByGeneric");
		List<Drug> drugsSearchResult =drugDaoService.findDrugByDrugGeneric(key);
		model.addAttribute("drugs", drugsSearchResult);
		return "drugs/drugsGenericSearch";
	}
	
	@RequestMapping("/index/drugByBrand/{key}")
	public String showDrugByBrand(@PathVariable String key, Model model) {

		slf4jLogger.info("IndexController :: showDrugByBrand");
		List<Drug> drugsSearchResult =drugDaoService.findDrugByDrugBrand(key);
		model.addAttribute("drugs", drugsSearchResult);
		return "drugs/drugsGenericSearch";
	}
	
	
	
	
	
	

}
