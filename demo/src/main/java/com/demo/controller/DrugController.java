package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.demo.commands.DrugSearchForm;
import com.demo.converter.DrugDataToDrugForm;
import com.demo.converter.DrugFormToDrugData;
import com.demo.domain.Drug;
import com.demo.domain.DrugGeneric;
import com.demo.domain.DrugManufacturer;
import com.demo.repositories.DrugGenericRepository;
import com.demo.repositories.DrugManufacturerRepository;
import com.demo.repositories.DrugRepository;
import com.demo.services.DrugService;
import com.googlecode.charts4j.BarChart;
import com.googlecode.charts4j.BarChartPlot;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.Data;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.LegendPosition;
import com.googlecode.charts4j.Marker;
import com.googlecode.charts4j.Markers;
import com.googlecode.charts4j.Plot;
import com.googlecode.charts4j.Plots;

@Controller
public class DrugController {

	private final Logger slf4jLogger = LoggerFactory.getLogger(DrugController.class);

	private DrugService drugService;
	private DrugRepository drugDaoService;
	private DrugFormToDrugData drugFormToDrugData;
	private DrugManufacturerRepository drugManufacturerDaoServie;
	private DrugGenericRepository drugGenericDaoService;
	private DrugDataToDrugForm drugDataToDrugForm;

	@Autowired
	public void setservices(DrugRepository drugDaoService, DrugFormToDrugData drugFormToDrugData,
			DrugManufacturerRepository drugManufacturerDaoServiec, DrugGenericRepository drugGenericDaoService,
			DrugDataToDrugForm drugDataToDrugForm) {

		this.drugDaoService = drugDaoService;
		this.drugFormToDrugData = drugFormToDrugData;
		this.drugManufacturerDaoServie = drugManufacturerDaoServiec;
		this.drugGenericDaoService = drugGenericDaoService;
		this.drugDataToDrugForm = drugDataToDrugForm;

	}

	@RequestMapping(value = "/drugList", method = RequestMethod.GET)
	public String druglist(Model model, Pageable pageable) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		slf4jLogger.info("DrugController ::druglist:: Drug page accessed by :" + name);

		model.addAttribute("drug", new DrugSearchForm());

		Page<Drug> drugsSearchResultPage = drugDaoService.findAllByOrderByDrugName(pageable);
		PageWrapper<Drug> page = new PageWrapper<Drug>(drugsSearchResultPage, "/drugList");
		model.addAttribute("drugs", drugsSearchResultPage);
		model.addAttribute("page", page);

		model.addAttribute("brands", drugManufacturerDaoServie.findAll());

		return "drugs/drugs";

	}

	@RequestMapping(value = "/drugSearch", method = RequestMethod.POST)
	public String drugSearch(DrugSearchForm form, BindingResult bindingResult, Model model) {

		if (form.getDrugName() == null && form.getManufacturerId() == 0 && form.getGenericName() == null) {
			return "redirect:/drugList";

		}

		// DrugGeneric generic = null;
		DrugManufacturer brand2 = null;

		Integer brandId = null;
		// Integer genericId= null;

		if (form.getManufacturerId() != 0) {
			brand2 = new DrugManufacturer();
			brand2.setManufacturerId(form.getManufacturerId());
			brandId = form.getManufacturerId();
			model.addAttribute("drugBrand", brand2);

		}

		model.addAttribute("drug", form);
		model.addAttribute("brands", drugManufacturerDaoServie.findAll());
		// model.addAttribute("generics", drugGenericDaoService.findAll());

		List<Drug> drugsSearchResult = drugDaoService.findDrugByDrugManufacturerOrByDrugGenericOrDrugName(brandId,
				form.getGenericName(), form.getDrugName());

		model.addAttribute("drugs", drugsSearchResult);
		return "drugs/drugs";
	}

	@RequestMapping("drug/new")
	public String newDrug(Model model) {
		DrugForm form = new DrugForm();
		model.addAttribute("generics", drugGenericDaoService.findAll());
		model.addAttribute("brands", drugManufacturerDaoServie.findAll());
		model.addAttribute("drugBrand", new DrugManufacturer());
		model.addAttribute("drugGeneric", new DrugGeneric());
		model.addAttribute("drug", form);
		return "drugs/drugForm";
	}

	@RequestMapping(value = "drug/saveDrug", method = RequestMethod.POST)
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

	@RequestMapping("drug/drugByGenericForComparison/{key}")
	public String showDrugByGenericForComparison(@PathVariable String key, Model model) {

		slf4jLogger.info("DrugController :: showDrugByGenericForComparison");
		List<Drug> drugsSearchResult = drugDaoService.findTop5ByDrugGeneric(Integer.parseInt(key));
		List<BarChartPlot> plots = new ArrayList();

		for (Drug d : drugsSearchResult) {
			String drugMarkerText = d.getDrugName() + " (" + d.getDrugprice() + ")";
			BarChartPlot currentCoverage = Plots.newBarChartPlot(Data.newData(d.getDrugprice()), Color.LIGHTSKYBLUE,
					d.getDrugName() + " (" + d.getDrugprice() + ")");
			Marker a = Markers.newTextMarker(drugMarkerText, Color.BLACK, 12);
			currentCoverage.addMarker(a, 0);
			plots.add(currentCoverage);

		}

		BarChart barChart = GCharts.newBarChart(plots);
		barChart.setTitle("Comparison for Generic: " + drugsSearchResult.get(0).getDrugGeneric().getGenericName(),
				Color.BLACK, 15);
		barChart.setSize(760, 320);
		barChart.setHorizontal(true);

		model.addAttribute("barUrl", barChart.toURLString());
		model.addAttribute("drugs", drugsSearchResult);

		return "drugs/drugsComparison";
	}

}
