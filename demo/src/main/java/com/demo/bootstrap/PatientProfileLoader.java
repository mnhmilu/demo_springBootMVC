package com.demo.bootstrap;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.demo.domain.Drug;
import com.demo.domain.DrugGeneric;
import com.demo.domain.DrugManufacturer;
import com.demo.repositories.DrugGenericRepository;
import com.demo.repositories.DrugManufacturerRepository;
import com.demo.repositories.DrugRepository;

@Component
public class PatientProfileLoader implements ApplicationListener<ContextRefreshedEvent> {

	private DrugManufacturerRepository drugManufacturerDaoService;

	private DrugGenericRepository drugGenericRepository;

	private DrugRepository drugRepository;

	private Logger log = Logger.getLogger(PatientProfileLoader.class);

	@Autowired
	public void setProductRepository(DrugRepository drugRepository,
			DrugManufacturerRepository drugManufacturerDaoService, DrugGenericRepository drugGenericRepository) {

		this.drugRepository = drugRepository;
		this.drugManufacturerDaoService = drugManufacturerDaoService;
		this.drugGenericRepository = drugGenericRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		DrugManufacturer manufacturer = new DrugManufacturer();
		manufacturer.setManufacturer("ESKAYEF");
		drugManufacturerDaoService.save(manufacturer);

		DrugManufacturer manufacturer2 = new DrugManufacturer();
		manufacturer2.setManufacturer("Sanofe");
		drugManufacturerDaoService.save(manufacturer2);

		DrugGeneric generic = new DrugGeneric();
		generic.setGenericName("Generic X");
		drugGenericRepository.save(generic);

		DrugGeneric generic2 = new DrugGeneric();
		generic2.setGenericName("Generic Y");
		drugGenericRepository.save(generic2);

		Drug newDrug = new Drug();

		newDrug.setDrugManufacturer(manufacturer);
		newDrug.setDrugGeneric(generic);
		newDrug.setDrugName("Alben");
		newDrug.setIndication("Alben is indicated in single and mixed infestations of:"
				+ "Enterobius vermicularis: Pinworm or threadworm" + "Trichuris trichiura: Whipworm"
				+ "Ascaris lumbricoides: Large roundworm" + "Ancylostoma duodenale: Hookworm"
				+ "Necator americanus: Hookworm" + "Taenia spp." + "Strongyloides stercoralis: Tapeworm");
		newDrug.setContraindication(
				"Because albendazole was found to be embryotoxic and teratogenic in rat and rabbit, its use is contraindicated in pregnant "
						+ "women or those likely to be pregnant. For women of childbearing age (15-45 years), "
						+ "Alben should be administered within 7 days of the start of normal menstruation");

		newDrug.setDosagesAdministration(
				"Adults of children over 2 years of age: 400 mg (2 Alben tablets or 1 Alben-DS tablet or 10 ml suspension) as a single dose in cases of Enterobius vermicularis, "
						+ "Trichuris trichiura, Ascaris lumbricoides, Ancylostoma duodenale and Necator americanus. In cases of strongyloidiasis or taeniasis, 400 mg (1 Alben-DS tablet or 10 ml suspension) as a single dose should be given for 3 consecutive days."
						+ "If the patient is not cured on follow-up after three weeks, a second course of treatment is indicated. Alben tablets may be chewed or swallowed but Alben – DS tablet must be chewed. Both the preparations may be crushed and mixed with food."
						+ " No specific procedures such as fasting or purging are required."
						+ "Children of 1-2 years: Recommended dose is a single dose of 200mg (1 Alben tablet or 1/2 Alben-DS tablet or 5 ml suspension)."
						+ "Children under 1 years: Not recommended");

		newDrug.setInsertDate(new Date());
		newDrug.setDrugprice(56.87);
		newDrug.setPackSize("5X5 size");

		drugRepository.save(newDrug);

	}
}
