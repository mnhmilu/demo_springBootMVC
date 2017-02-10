package com.demo.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	private final Logger slf4jLogger = LoggerFactory.getLogger(DrugGenericController.class);

	private DrugGenericRepository drugGenericDaoService;

	@Autowired
	public void setservices(DrugGenericRepository drugGenericDaoService) {

		this.drugGenericDaoService = drugGenericDaoService;

	}

	@RequestMapping(value = "admin/drugGenericList", method = RequestMethod.GET)
	public String listGeneric(Model model, Pageable pageable) {

		model.addAttribute("drugGeneric", new DrugGenericSearchForm());

		// model.addAttribute("drugGenerics", drugGenericDaoService.findAll());

		Page<DrugGeneric> productPage = drugGenericDaoService.findAll(pageable);
		PageWrapper<DrugGeneric> page = new PageWrapper<DrugGeneric>(productPage, "/admin/drugGenericList");
		model.addAttribute("drugGenerics", page.getContent());
		model.addAttribute("page", page);

		return "drugGeneric/drugsGenerics";

	}

	@RequestMapping(value = "admin/drugGenericSearch", method = RequestMethod.POST)
	public String drugGenericSearch(DrugGenericSearchForm form, BindingResult bindingResult, Model model) {

		if (form.getGenericName() == null) {
			return "redirect:/admin/drugGenericList";
		}

		model.addAttribute("drugGeneric", form);

		List<DrugGeneric> drugsGenericSearchResult = drugGenericDaoService
				.findDrugGenericByGenericNameIgnoreCase(form.getGenericName());

		model.addAttribute("drugGenerics", drugsGenericSearchResult);
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

		DrugGeneric generic = new DrugGeneric();
		generic.setGenericName(form.getGenericName());
		generic.setRemarks(form.getRemarks());
		generic.setIdGeneric(form.getIdGeneric());
		generic.setInsertDate(new Date());
		drugGenericDaoService.save(generic);

		return "redirect:/admin/drugGenericList";

	}

	@RequestMapping("admin/generic/edit/{idGeneric}")
	public String editGeneric(@PathVariable Integer idGeneric, Model model) {
		DrugGenericForm form = new DrugGenericForm();
		DrugGeneric generic = drugGenericDaoService.findByIdGeneric(idGeneric);
		form.setGenericName(generic.getGenericName());
		form.setRemarks(generic.getRemarks());
		form.setIdGeneric(generic.getIdGeneric());
		model.addAttribute("drugGeneric", form);
		return "drugGeneric/drugGenericForm";

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
