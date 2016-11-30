package com.demo.controller;

import java.util.Date;
import java.util.List;

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

import com.demo.commands.DrugGenericForm;
import com.demo.commands.DrugGenericSearchForm;
import com.demo.domain.DrugGeneric;
import com.demo.repositories.DrugGenericRepository;

@Controller
public class DrugGenericController {

	private final Logger slf4jLogger = LoggerFactory
			.getLogger(DrugGenericController.class);

	private DrugGenericRepository drugGenericDaoService;

	@Autowired
	public void setservices(DrugGenericRepository drugGenericDaoService) {

		this.drugGenericDaoService = drugGenericDaoService;

	}

	@RequestMapping(value = "/drugGenericList", method = RequestMethod.GET)
	public String listGeneric(Model model) {

		model.addAttribute("drugGeneric", new DrugGenericSearchForm());
		model.addAttribute("drugGenerics", drugGenericDaoService.findAll());
		return "drugGeneric/drugsGenerics";

	}

	@RequestMapping(value = "/drugGenericSearch", method = RequestMethod.POST)
	public String drugGenericSearch(DrugGenericSearchForm form,
			BindingResult bindingResult, Model model) {

		if (form.getGenericName() == null) {
			return "redirect:/drugGenericList";
		}

		model.addAttribute("drugGeneric", form);

		List<DrugGeneric> drugsGenericSearchResult = drugGenericDaoService
				.findDrugGenericByGenericNameIgnoreCase(form.getGenericName());

		model.addAttribute("drugGenerics", drugsGenericSearchResult);
		return "drugGeneric/drugsGenerics";
	}

	@RequestMapping("generic/new")
	public String newDrugGeneric(Model model) {
		DrugGenericForm form = new DrugGenericForm();
		model.addAttribute("drugGeneric", form);
		return "drugGeneric/drugGenericForm";
	}

	@RequestMapping(value = "generics", method = RequestMethod.POST)
	public String saveDrugGeneric(@Valid @ModelAttribute("drug") DrugGenericForm form,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("drugGeneric", form);

			return "drugs/drugGenericForm";
		}

		DrugGeneric generic = new DrugGeneric();
		generic.setGenericName(form.getGenericName());
		generic.setRemarks(form.getRemarks());
		generic.setIdGeneric(form.getIdGeneric());
		generic.setInsertDate(new Date());
		drugGenericDaoService.save(generic);

		return "redirect:/drugGenericList";

	}

	@RequestMapping("generic/edit/{idGeneric}")
	public String editGeneric(@PathVariable Integer idGeneric, Model model) {
		DrugGenericForm form = new DrugGenericForm();
		DrugGeneric generic = drugGenericDaoService.findByIdGeneric(idGeneric);
		form.setGenericName(generic.getGenericName());
		form.setRemarks(generic.getRemarks());
		form.setIdGeneric(generic.getIdGeneric());
		model.addAttribute("drugGeneric", form);
		return "drugGeneric/drugGenericForm";

	}

	@RequestMapping("generic/delete/{id}")
	public String deleteGeneric(@PathVariable Integer id) {
		try {

			drugGenericDaoService.delete(id);
			return "redirect:/drugGenericList";

		} catch (Exception ex) {

			return "redirect:/drugGenericList";

		}
	}

}
