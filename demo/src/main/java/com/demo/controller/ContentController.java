package com.demo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import com.demo.converter.ContentDataToContentForm;
import com.demo.domain.Content;
import com.demo.domain.Drug;
import com.demo.enums.ContentType;
import com.demo.repositories.ContentRepository;

@Controller
//@RequestMapping(value = "/api")
public class ContentController {

	private final Logger slf4jLogger = LoggerFactory.getLogger(ContentController.class);

	private ContentRepository contentRepository;

	private ContentDataToContentForm contentDataToContentForm;

	@Autowired
	public void setservices(ContentRepository contentRepository, ContentDataToContentForm contentDataToContentForm) {

		this.contentRepository = contentRepository;
		this.contentDataToContentForm = contentDataToContentForm;

	}

	@RequestMapping(value = "admin/addList", method = RequestMethod.GET)
	public String addlist(Model model, Pageable pageable) {

		model.addAttribute("content", new ContentSearchForm());
		Page<Content> results = contentRepository.findContentByContentTypeOrByHeaderOrderByInsertDateDesc(
				ContentType.Advertisement.name(), null, pageable);
		
		
		PageWrapper<Content> page = new PageWrapper<Content>(results, "/admin/addList");
		model.addAttribute("contents", results);
		model.addAttribute("page", page);
		return "contents/addcontents";

	}

	@RequestMapping(value = "admin/newsList", method = RequestMethod.GET)
	public String newslist(Model model, Pageable pageable) {

		model.addAttribute("content", new ContentSearchForm());
		Page<Content> results = contentRepository
				.findContentByContentTypeOrByHeaderOrderByInsertDateDesc(ContentType.News.name(), null, pageable);
		PageWrapper<Content> page = new PageWrapper<Content>(results, "/admin/newsList");
		model.addAttribute("contents", results);
		model.addAttribute("page", page);
		return "contents/newscontents";

	}

	@RequestMapping(value = "admin/drugUpdateList", method = RequestMethod.GET)
	public String list(Model model, Pageable pageable) {

		model.addAttribute("content", new ContentSearchForm());
		Page<Content> results = contentRepository
				.findContentByContentTypeOrByHeaderOrderByInsertDateDesc(ContentType.DrugUpdate.name(), null, pageable);
		PageWrapper<Content> page = new PageWrapper<Content>(results, "/admin/drugUpdateList");
		model.addAttribute("contents", results);
		model.addAttribute("page", page);
		return "contents/drugUpdateContents";

	}

	@RequestMapping(value = "admin/addContentSearch", method = RequestMethod.POST)
	public String addContentSearch(ContentSearchForm form, BindingResult bindingResult, Model model,
			Pageable pageable) {

		if (form.getHeader().trim() == "") {
			return "redirect:/admin/addList";
		}

		Page<Content> results = contentRepository.findContentByContentTypeOrByHeaderOrderByInsertDateDesc(null,
				form.getHeader(), pageable);
		PageWrapper<Content> page = new PageWrapper<Content>(results, "/admin/addContentSearch");
		model.addAttribute("content", form);
		model.addAttribute("contents", results);
		model.addAttribute("page", page);
		return "contents/addcontents";
	}

	@RequestMapping(value = "admin/newsContentSearch", method = RequestMethod.POST)
	public String newsContentSearch(ContentSearchForm form, BindingResult bindingResult, Model model,
			Pageable pageable) {

		if (form.getHeader().trim() == "") {
			return "redirect:/admin/newsList";
		}

		Page<Content> results = contentRepository.findContentByContentTypeOrByHeaderOrderByInsertDateDesc(null,
				form.getHeader(), pageable);
		PageWrapper<Content> page = new PageWrapper<Content>(results, "/admin/newsContentSearch");
		model.addAttribute("content", form);
		model.addAttribute("contents", results);
		model.addAttribute("page", page);
		return "contents/newscontents";
	}

	@RequestMapping(value = "admin/drugUpdateContentSearch", method = RequestMethod.POST)
	public String drugUpdateContentSearch(ContentSearchForm form, BindingResult bindingResult, Model model,
			Pageable pageable) {

		if (form.getHeader().trim() == "") {
			return "redirect:/admin/drugUpdateList";
		}

		Page<Content> results = contentRepository.findContentByContentTypeOrByHeaderOrderByInsertDateDesc(null,
				form.getHeader(), pageable);
		PageWrapper<Content> page = new PageWrapper<Content>(results, "/admin/drugUpdateContentSearch");
		model.addAttribute("content", form);
		model.addAttribute("contents", results);
		model.addAttribute("page", page);
		return "contents/drugUpdateContents";
	}

	@RequestMapping("admin/content/newAdd")
	public String newAddvertisement(Model model) throws ParseException {

		ContentForm form = new ContentForm();

		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.DATE, 7);

		Date currentDatePlusOne = c.getTime();
		form.setExpireDate(currentDatePlusOne);
		model.addAttribute("content", form);

		return "contents/addContentForm";
	}

	@RequestMapping("admin/content/newDrugUpdate")
	public String newDrugUpdate(Model model) throws ParseException {

		ContentForm form = new ContentForm();

		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.DATE, 7);

		Date currentDatePlusOne = c.getTime();
		form.setExpireDate(currentDatePlusOne);
		model.addAttribute("content", form);

		return "contents/drugUpdateContentForm";
	}

	@RequestMapping("admin/content/newNews")
	public String newNews(Model model) throws ParseException {

		ContentForm form = new ContentForm();

		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);
		c.add(Calendar.DATE, 7);

		Date currentDatePlusOne = c.getTime();
		form.setExpireDate(currentDatePlusOne);
		model.addAttribute("content", form);

		return "contents/newsContentForm";
	}

	@RequestMapping(value = "admin/saveAddContent", method = RequestMethod.POST)
	public String saveAddContent(@RequestParam("file") MultipartFile file,
			@Valid @ModelAttribute("content") ContentForm form, BindingResult bindingResult, Model model,HttpSession session)
			throws IOException {

		if (bindingResult.hasErrors()) {

			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date today = new Date();
			dateFormat.format(today);
			form.setExpireDate(today);
			model.addAttribute("content", form);
			return "contents/addContentForm";
		}
		
		if(file.getBytes().length ==0)
		{
			Content content=(Content ) session.getAttribute("storedContent");
			if(content !=null)
			{
		    	form.setImage(content.getImage());			
			}
		}
		else if(file.getBytes().length >300000)
		{
			slf4jLogger.warn("ContentController ::saveAddContent:: photo size limit exceeded");		
			model.addAttribute("errorMessage", "Eror! Photo size limit exceeded, try to upload photo < 300 KB size");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date today = new Date();
			dateFormat.format(today);
			form.setExpireDate(today);
			model.addAttribute("content", form);
	
			return "contents/addContentForm";
		}
		else
		{		
		  form.setImage(file.getBytes());	 
		  
		}
		
		
		
		

		// add custom validation here

		Content content = new Content();
		content.setId(form.getId());
		content.setAddSection(form.getAdd_section());
		content.setContent_details(form.getContent_details());
		content.setImage(form.getImage());
		content.setContentPage(form.getContentPage());

		content.setContent_summary(form.getContent_summary());
		content.setContentType(ContentType.Advertisement.name());
		content.setExpireDate(form.getExpireDate());
		content.setHeader(form.getHeader());
		content.setInsertDate(new Date());
		content.setOriginalFileName(file.getOriginalFilename());
		content.setLastUpdatedDate(new Date());

		contentRepository.save(content);

		//return "redirect:/admin/content/newAdd";
		
		if(form.getId()==null)
		{
		
		   return "redirect:/admin/content/newAdd";
		}
		else
		{			
			return "redirect:/admin/addList";					
			
		}

	}

	@RequestMapping(value = "admin/saveNewsContent", method = RequestMethod.POST)
	public String saveNewsContent(@RequestParam("file") MultipartFile file,
			@Valid @ModelAttribute("content") ContentForm form, BindingResult bindingResult, Model model,HttpSession session)
			throws IOException {

		if (bindingResult.hasErrors()) {

			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date today = new Date();
			dateFormat.format(today);
			form.setExpireDate(today);
			model.addAttribute("content", form);
			return "contents/newsContentForm";
		}	
		

		if(file.getBytes().length ==0)
		{
			Content content=(Content ) session.getAttribute("storedContent");
			
			if(content !=null)
			{
			 form.setImage(content.getImage());			
			}
		}
		else if(file.getBytes().length >300000)
		{
			slf4jLogger.warn("ContentController ::saveAddContent:: photo size limit exceeded");		
			model.addAttribute("errorMessage", "Eror! Photo size limit exceeded, try to upload photo < 300 KB size");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date today = new Date();
			dateFormat.format(today);
			form.setExpireDate(today);
			model.addAttribute("content", form);
			return "contents/addContentForm";
		}
		else
		{		
		    form.setImage(file.getBytes());
		}

		// add custom validation here

		Content content = new Content();
		content.setId(form.getId());
		content.setContent_details(form.getContent_details());
		content.setImage(form.getImage());
		content.setContentPage("Index");
		content.setContent_summary(form.getContent_summary());
		content.setContentType(ContentType.News.name());
		content.setExpireDate(form.getExpireDate());
		content.setHeader(form.getHeader());
		content.setInsertDate(new Date());
		content.setOriginalFileName(file.getOriginalFilename());
		content.setLastUpdatedDate(new Date());

		contentRepository.save(content);
		
		if(form.getId()==null)
		{
		
		   return "redirect:/admin/content/newNews";
		}
		else
		{			
			return "redirect:/admin/newsList";					
			
		}

		//return "redirect:/admin/content/newNews";

	}

	@RequestMapping(value = "admin/saveDrugUpdateContent", method = RequestMethod.POST)
	public String saveContent(@RequestParam("file") MultipartFile file,
			@Valid @ModelAttribute("content") ContentForm form, BindingResult bindingResult, Model model,HttpSession session)
			throws IOException {

		if (bindingResult.hasErrors()) {

			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date today = new Date();
			dateFormat.format(today);
			form.setExpireDate(today);
			model.addAttribute("content", form);
			return "contents/drugUpdateContentForm";
		}
		
		if(file.getBytes().length ==0)
		{
			Content content=(Content ) session.getAttribute("storedContent");
			
			if(content !=null)
			{
		 	form.setImage(content.getImage());			
			}
		}
		else if(file.getBytes().length >300000)
		{
			slf4jLogger.warn("ContentController ::saveAddContent:: photo size limit exceeded");		
			model.addAttribute("errorMessage", "Eror! Photo size limit exceeded, try to upload photo < 300 KB size");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date today = new Date();
			dateFormat.format(today);
			form.setExpireDate(today);
			model.addAttribute("content", form);
			return "contents/addContentForm";
		}
		else
		{		
		    form.setImage(file.getBytes());
		}

		// add custom validation here

		Content content = new Content();
		content.setId(form.getId());
		content.setContent_details(form.getContent_details());
		content.setImage(form.getImage());
		content.setContentPage("Index");
		content.setDrugUpdateType(form.getDrugUpdateType());

		content.setContent_summary(form.getContent_summary());
		content.setContentType(ContentType.DrugUpdate.name());
		content.setExpireDate(form.getExpireDate());
		content.setHeader(form.getHeader());
		content.setInsertDate(new Date());
		content.setOriginalFileName(file.getOriginalFilename());
		content.setLastUpdatedDate(new Date());
		
		contentRepository.save(content);

		
		if(form.getId()==null)
		{
		
		   return "redirect:/admin/content/newDrugUpdate";
		}
		else
		{			
			return "redirect:/admin/drugUpdateList";					
			
		}

	}

	@RequestMapping("admin/addContentDetails/{id}")
	public String showAddContent(@PathVariable Integer id, Model model, HttpSession session) {

		slf4jLogger.info("ContentController :: showContent");
		Content content = contentRepository.findById(id);
		ContentForm form = contentDataToContentForm.convert(content);
		model.addAttribute("content", form);
		model.addAttribute("imageid", form.getAdd_section());
		session.setAttribute("storedContent", content);
		return "contents/addContentshow";

	}

	@RequestMapping("admin/newsContentDetails/{id}")
	public String showNewsContent(@PathVariable Integer id, Model model, HttpSession session) {

		slf4jLogger.info("ContentController :: showContent");
		Content content = contentRepository.findById(id);
		ContentForm form = contentDataToContentForm.convert(content);
		model.addAttribute("content", form);
		//model.addAttribute("imageid", form.getAdd_section());
		session.setAttribute("storedContent", content);
		return "contents/newsContentshow";

	}

	@RequestMapping("admin/drugUpdateContentDetails/{id}")
	public String showDrugUpdateContent(@PathVariable Integer id, Model model, HttpSession session) {

		slf4jLogger.info("ContentController :: showContent");
		Content content = contentRepository.findById(id);
		ContentForm form = contentDataToContentForm.convert(content);
		model.addAttribute("content", form);
		//model.addAttribute("imageid", form.getAdd_section());
		session.setAttribute("storedContent", content);
		return "contents/drugUpdateContentshow";

	}

	@RequestMapping("admin/content/editAddContent/{id}")
	public String editAddContent(@PathVariable Integer id, Model model, HttpSession session) {

		Content content = contentRepository.findById(id);

		session.setAttribute("storedContent", content);

		ContentForm form = contentDataToContentForm.convert(content);

		model.addAttribute("content", form);

		return "contents/addContentForm";

	}

	@RequestMapping("admin/content/editNewsContent/{id}")
	public String editNewsContent(@PathVariable Integer id, Model model, HttpSession session) {

		Content content = contentRepository.findById(id);

		session.setAttribute("storedContent", content);

		ContentForm form = contentDataToContentForm.convert(content);

		model.addAttribute("content", form);

		return "contents/newsContentForm";

	}

	@RequestMapping("admin/content/editDrugUpdate/{id}")
	public String editContent(@PathVariable Integer id, Model model,HttpSession session) {
		
		Content content = contentRepository.findById(id);

		session.setAttribute("storedContent", content);

		ContentForm form = contentDataToContentForm.convert(content);

		model.addAttribute("content", form);

		return "contents/drugUpdateContentForm";

	}

	@RequestMapping("admin/content/deleteNews/{id}")
	public String deleteNews(@PathVariable Integer id) {
		try {

			contentRepository.delete(id);
			return "redirect:/admin/newsList";

		} catch (Exception ex) {

			return "redirect:/admin/newsList";

		}
	}

	@RequestMapping("admin/content/deleteDrugUpdate/{id}")
	public String deleteDrugUpdate(@PathVariable Integer id) {
		try {

			contentRepository.delete(id);
			return "redirect:/admin/drugUpdateList";

		} catch (Exception ex) {

			return "redirect:/admin/drugUpdateList";

		}
	}

	@RequestMapping("admin/content/deleteAdd/{id}")
	public String deleteAdd(@PathVariable Integer id) {
		try {

			contentRepository.delete(id);
			return "redirect:/admin/addList";

		} catch (Exception ex) {

			return "redirect:/admin/addList";

		}
	}
	
	@RequestMapping(value = "content/additionalContentList/{idadditionalContentType}", method = RequestMethod.GET)
	public String additionalContentList(@PathVariable Integer idadditionalContentType, Pageable pageable, Model model) {
		
		
		if(pageable.getPageSize()>20)
		{
			
			slf4jLogger.warn("ContentController ::additionalContentList:: Possible Temparing Attempt");
			return "redirect:/";
			
		}

		model.addAttribute("content", new ContentSearchForm());
		Page<Content> results = contentRepository
				.findContentByAdditionalContentType(idadditionalContentType, pageable);
		PageWrapper<Content> page = new PageWrapper<Content>(results, "/content/additionalContentList/"+idadditionalContentType);
		model.addAttribute("contents", results);
		model.addAttribute("page", page);
		return "contents/additionalContents";

	}
	
	
	@RequestMapping("content/ContentDetails/{id}")
	public String showDetailContent(@PathVariable Integer id, Model model,HttpSession session) {
		
		Content content = contentRepository.findById(id);		

		ContentForm form = contentDataToContentForm.convert(content);		
		
		session.setAttribute("storedContent", content);

		model.addAttribute("content", form);

		return "contents/contentDetails";

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

	@RequestMapping(value = "/content/image/{imageid}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@PathVariable final String imageid, HttpSession session) {

		byte[] bytes = null;

		Content content = (Content) session.getAttribute("storedContent");

		if (content != null) {
			bytes = content.getImage();
		}

		// Set headers
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		
	

		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}

}
