package com.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.commands.DrugForm;
import com.demo.commands.DrugSearchForm;
import com.demo.converter.DrugDataToDrugForm;
import com.demo.domain.Content;
import com.demo.domain.Drug;
import com.demo.repositories.ContentRepository;
import com.demo.repositories.DrugGenericRepository;
import com.demo.repositories.DrugRepository;

@Controller
public class IndexController {

	private DrugRepository drugDaoService;
	private DrugDataToDrugForm drugDataToDrugForm;
	private DrugGenericRepository drugGenericDaoService;
	private ContentRepository contentRepository;
	private CounterService cournterService;

	private final Logger slf4jLogger = LoggerFactory.getLogger(DrugController.class);

	@Autowired
	public void setservices(DrugRepository drugDaoService, DrugDataToDrugForm drugDataToDrugForm,
			DrugGenericRepository drugGenericDaoService, ContentRepository contentRepository,CounterService cournterService) {

		this.drugDaoService = drugDaoService;
		this.drugDataToDrugForm = drugDataToDrugForm;
		this.drugGenericDaoService = drugGenericDaoService;
		this.contentRepository = contentRepository;
		this.cournterService=cournterService;

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model)  {
		
		cournterService.increment("Calling Index");
		model.addAttribute("drug", new DrugSearchForm());
		model.addAttribute("drugCount", drugDaoService.count());
		model.addAttribute("genericCount", drugGenericDaoService.count());
		model.addAttribute("imageid", "A1");

		return "index";

	}

	@RequestMapping(value = "/drugSearchFromIndex", method = RequestMethod.POST)
	public String drugSearch(DrugSearchForm form, BindingResult bindingResult, Model model) {

		if (form.getDrugName() == null || form.getDrugName().trim() == "") {
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
		List<Drug> drugsSearchResult = drugDaoService.findDrugByDrugGeneric(key);
		model.addAttribute("drugs", drugsSearchResult);
		return "drugs/drugsGenericSearch";
	}

	@RequestMapping("/index/drugByBrand/{key}")
	public String showDrugByBrand(@PathVariable String key, Model model) {

		slf4jLogger.info("IndexController :: showDrugByBrand");
		List<Drug> drugsSearchResult = drugDaoService.findDrugByDrugBrand(key);
		model.addAttribute("drugs", drugsSearchResult);
		return "drugs/drugsGenericSearch";
	}


	@RequestMapping(value = "/index/image/{imageid}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getQRImage(@PathVariable final String imageid)  {

		byte[] bytes = null;
		
		if (contentRepository.count() > 0) {
			Content content = contentRepository.findTop50ByOrderByInsertDateDesc().get(0);
			bytes = content.getImage();
		}

		// Set headers
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}

}
