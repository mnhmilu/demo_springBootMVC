package com.demo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.demo.commands.ContentForm;
import com.demo.commands.ContentSearchForm;
import com.demo.domain.Content;
import com.demo.repositories.ContentRepository;
import com.demo.utility.fileUploader.StorageService;

@Controller
public class ContentController {

	private final Logger slf4jLogger = LoggerFactory.getLogger(ContentController.class);

	private ContentRepository contentRepository;
	private StorageService storageService;

	@Autowired
	public void setservices(ContentRepository contentRepository, StorageService storageService) {

		this.contentRepository = contentRepository;
		this.storageService = storageService;

	}

	@RequestMapping(value = "/contentList", method = RequestMethod.GET)
	public String list(Model model) {

		model.addAttribute("content", new ContentSearchForm());
		model.addAttribute("contents", contentRepository.findTop50ByOrderByInsertDateDesc());
		return "contents/contents";

	}

	@RequestMapping("content/new")
	public String newDrug(Model model) throws ParseException {

		ContentForm form = new ContentForm();
		//SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.DATE, 7);

		Date currentDatePlusOne = c.getTime();	
		form.setExpireDate(currentDatePlusOne);
		model.addAttribute("content", form);

		return "contents/contentForm";
	}

	@RequestMapping(value = "content", method = RequestMethod.POST)
	public String saveDrug(@RequestParam("file") MultipartFile file, @Valid @ModelAttribute("content") ContentForm form,
			BindingResult bindingResult, Model model) throws IOException {

		if (bindingResult.hasErrors() || form.getContentType().equalsIgnoreCase(",0")) {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date today = new Date();
			dateFormat.format(today);
			form.setExpireDate(today);
			model.addAttribute("content", form);
			return "contents/contentForm";
		}

		String[] parts = form.getContentType().split(",");
		String contentType = parts[0];		

		Content content = new Content();
		content.setAdd_section(form.getAdd_section());
		content.setContent_details(form.getContent_details());
		content.setImage(file.getBytes());
		
        content.setDrugUpdateType(form.getDrugUpdateType());
		
		content.setContent_summary(form.getContent_summary());
		content.setContentType(contentType);
		content.setExpireDate(form.getExpireDate());
		content.setHeader(form.getHeader());
		content.setInsertDate(new Date());
		content.setOriginalFileName(file.getOriginalFilename());	
		content.setAdd_section(form.getAdd_section());

		contentRepository.save(content);

	
		return "redirect:/content/new";

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

	/*
	 * 
	 * @RequestMapping(value = "/drugSearch", method = RequestMethod.POST)
	 * public String drugSearch(DrugSearchForm form, BindingResult
	 * bindingResult, Model model) {
	 * 
	 * if (form.getDrugName() == null && form.getManufacturerId() == 0 &&
	 * form.getGenericName()== null) { return "redirect:/drugList";
	 * 
	 * }
	 * 
	 * //DrugGeneric generic = null; DrugManufacturer brand2 = null;
	 * 
	 * Integer brandId =null; //Integer genericId= null;
	 * 
	 * 
	 * 
	 * if(form.getManufacturerId()!=0) { brand2 = new DrugManufacturer();
	 * brand2.setManufacturerId(form.getManufacturerId()); brandId =
	 * form.getManufacturerId(); model.addAttribute("drugBrand", brand2);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * model.addAttribute("drug", form); model.addAttribute("brands",
	 * drugManufacturerDaoServie.findAll()); //model.addAttribute("generics",
	 * drugGenericDaoService.findAll());
	 * 
	 * 
	 * List<Drug> drugsSearchResult =drugDaoService
	 * .findDrugByDrugManufacturerOrByDrugGenericOrDrugName(brandId,form.
	 * getGenericName(), form.getDrugName());
	 * 
	 * 
	 * model.addAttribute("drugs",drugsSearchResult ); return "drugs/drugs"; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @RequestMapping("drug/{id}") public String showDrug(@PathVariable Integer
	 * id, Model model) {
	 * 
	 * slf4jLogger.info("DrugController :: showDrug");
	 * 
	 * DrugForm form = drugDataToDrugForm.convert(drugDaoService.findById(id));
	 * model.addAttribute("drug", form); return "drugs/drugshow"; }
	 * 
	 * @RequestMapping("drug/edit/{id}") public String edit(@PathVariable
	 * Integer id, Model model) {
	 * 
	 * DrugForm form = drugDataToDrugForm.convert(drugDaoService.findById(id));
	 * model.addAttribute("generics", drugGenericDaoService.findAll());
	 * model.addAttribute("brands", drugManufacturerDaoServie.findAll());
	 * 
	 * model.addAttribute("drug", form);
	 * 
	 * DrugGeneric generic = new DrugGeneric();
	 * generic.setGenericName(form.getGenericName());
	 * generic.setIdGeneric(form.getGenericId());
	 * model.addAttribute("drugGeneric", generic);
	 * 
	 * DrugManufacturer brand2 = new DrugManufacturer();
	 * brand2.setManufacturer(form.getManufacturer());
	 * brand2.setManufacturerId(form.getManufacturerId());
	 * model.addAttribute("drugBrand", brand2);
	 * 
	 * return "drugs/drugForm";
	 * 
	 * }
	 * 
	 * @RequestMapping("drug/delete/{id}") public String delete(@PathVariable
	 * Integer id) { try {
	 * 
	 * drugDaoService.delete(id); return "redirect:/drugList";
	 * 
	 * } catch (Exception ex) {
	 * 
	 * return "redirect:/drugList";
	 * 
	 * } }
	 * 
	 */

}
