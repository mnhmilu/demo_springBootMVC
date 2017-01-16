package com.demo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.demo.commands.ContentForm;
import com.demo.commands.ContentSearchForm;
import com.demo.commands.DrugForm;
import com.demo.converter.ContentDataToContentForm;
import com.demo.domain.Content;
import com.demo.domain.DrugGeneric;
import com.demo.domain.DrugManufacturer;
import com.demo.repositories.ContentRepository;

@Controller
public class ContentController {

	private final Logger slf4jLogger = LoggerFactory.getLogger(ContentController.class);

	private ContentRepository contentRepository;

	private ContentDataToContentForm contentDataToContentForm;

	@Autowired
	public void setservices(ContentRepository contentRepository, ContentDataToContentForm contentDataToContentForm) {

		this.contentRepository = contentRepository;
		this.contentDataToContentForm = contentDataToContentForm;

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
		// SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

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
	public String saveContent(@RequestParam("file") MultipartFile file,
			@Valid @ModelAttribute("content") ContentForm form, BindingResult bindingResult, Model model)
			throws IOException {

		if (bindingResult.hasErrors() || form.getContentType().equalsIgnoreCase(",0")) {

			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date today = new Date();
			dateFormat.format(today);
			form.setExpireDate(today);
			model.addAttribute("content", form);
			return "contents/contentForm";
		}

		// add custom validation here

		String[] parts = form.getContentType().split(",");
		String contentType = parts[0];

		Content content = new Content();
		content.setId(form.getId());
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

	@RequestMapping("content/{id}")
	public String showContent(@PathVariable Integer id, Model model) {

		slf4jLogger.info("ContentController :: showContent");
		ContentForm form = contentDataToContentForm.convert(contentRepository.findById(id));
		model.addAttribute("content", form);
		model.addAttribute("imageid", "A1");
		return "contents/contentshow";

	}

	@RequestMapping("content/edit/{id}")
	public String editContent(@PathVariable Integer id, Model model) {

		ContentForm form = contentDataToContentForm.convert(contentRepository.findById(id));

		model.addAttribute("content", form);

		return "contents/contentForm";

	}

	@RequestMapping("content/delete/{id}")
	public String delete(@PathVariable Integer id) {
		try {

			contentRepository.delete(id);
			return "redirect:/contentList";

		} catch (Exception ex) {

			return "redirect:/contentList";

		}
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
	 * 
	 * 
	 * 
	 * 
	 */

}
