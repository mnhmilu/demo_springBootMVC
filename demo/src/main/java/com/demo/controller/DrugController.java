package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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
	public String list(Model model) {

		model.addAttribute("drug", new DrugSearchForm());
		model.addAttribute("drugs", drugDaoService.findTop50ByOrderByInsertDateDesc());
		model.addAttribute("brands", drugManufacturerDaoServie.findAll());
		// model.addAttribute("generics", drugGenericDaoService.findAll());

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

		List<Drug> drugsFilteredList = new ArrayList();

		int topLimit = 0;

		if (drugsSearchResult.size() <= 5) {
			topLimit = drugsSearchResult.size();
		} else {
			topLimit = 5;
		}

		drugsFilteredList = drugsSearchResult.subList(0, topLimit);

		// BarChartPlot currentCoverage=null;
		List<BarChartPlot> plots = new ArrayList();

		if (drugsFilteredList.get(0) != null) {
			BarChartPlot currentCoverage = Plots.newBarChartPlot(Data.newData(drugsFilteredList.get(0).getDrugprice()),
					Color.CRIMSON,
					drugsFilteredList.get(0).getDrugName() + " (" + drugsFilteredList.get(0).getDrugprice() + ")");
			plots.add(currentCoverage);
		}

		if (drugsFilteredList.size() > 1) {

			if (drugsFilteredList.get(1) != null) {
				BarChartPlot currentCoverage1 = Plots.newBarChartPlot(
						Data.newData(drugsFilteredList.get(1).getDrugprice()), Color.ORANGERED,
						drugsFilteredList.get(1).getDrugName() + " (" + drugsFilteredList.get(1).getDrugprice() + ")");
				plots.add(currentCoverage1);
			}
		}
		if (drugsFilteredList.size() > 2) {

			if (drugsFilteredList.get(2) != null) {
				BarChartPlot currentCoverage2 = Plots.newBarChartPlot(
						Data.newData(drugsFilteredList.get(2).getDrugprice()), Color.ORANGE,
						drugsFilteredList.get(2).getDrugName() + " (" + drugsFilteredList.get(2).getDrugprice() + ")");
				plots.add(currentCoverage2);
			}
		}
		if (drugsFilteredList.size() > 3) {
			if (drugsFilteredList.get(3) != null) {
				BarChartPlot currentCoverage3 = Plots.newBarChartPlot(
						Data.newData(drugsFilteredList.get(3).getDrugprice()), Color.YELLOW,
						drugsFilteredList.get(3).getDrugName() + " (" + drugsFilteredList.get(3).getDrugprice() + ")");
				plots.add(currentCoverage3);
			}
		}
		if (drugsFilteredList.size() > 4) {
			if (drugsFilteredList.get(4) != null) {
				BarChartPlot currentCoverage4 = Plots.newBarChartPlot(
						Data.newData(drugsFilteredList.get(4).getDrugprice()), Color.GREEN,
						drugsFilteredList.get(4).getDrugName() + " (" + drugsFilteredList.get(4).getDrugprice() + ")");
				plots.add(currentCoverage4);
			}
		}

		BarChart barChart = GCharts.newBarChart(plots);

		// BarChart barChart = GCharts.newBarChart(currentCoverage,
		// currentCoverage2);
		barChart.setTitle("Drug Price Comparison for Generic: "+drugsSearchResult.get(0).getDrugGeneric().getGenericName(), Color.BLACK, 15);
		barChart.setSize(760, 320);
		barChart.setHorizontal(true);

		model.addAttribute("barUrl", barChart.toURLString());
		model.addAttribute("drugs", drugsFilteredList);

		return "drugs/drugsComparison";
	}

}
