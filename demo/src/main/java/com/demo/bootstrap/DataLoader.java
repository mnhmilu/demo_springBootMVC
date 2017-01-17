package com.demo.bootstrap;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.demo.domain.Content;
import com.demo.domain.DoctorsInfo;
import com.demo.domain.DoctorsSpecialization;
import com.demo.domain.Drug;
import com.demo.domain.DrugGeneric;
import com.demo.domain.DrugManufacturer;
import com.demo.repositories.ContentRepository;
import com.demo.repositories.DoctorsInfoRepository;
import com.demo.repositories.DoctorsSpecializaitonRepository;
import com.demo.repositories.DrugGenericRepository;
import com.demo.repositories.DrugManufacturerRepository;
import com.demo.repositories.DrugRepository;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private DrugManufacturerRepository drugManufacturerDaoService;

	private DrugGenericRepository drugGenericRepository;

	private DrugRepository drugRepository;

	private DoctorsInfoRepository doctorsInfoRepository;

	private DoctorsSpecializaitonRepository doctorsSpecializaitonRepository;

	private ContentRepository contentRepository;

	private Logger log = Logger.getLogger(DataLoader.class);

	@Autowired
	public void setProductRepository(DrugRepository drugRepository,
			DrugManufacturerRepository drugManufacturerDaoService, DrugGenericRepository drugGenericRepository,
			DoctorsInfoRepository doctorsInfoRepository,
			DoctorsSpecializaitonRepository doctorsSpecializaitonRepository, ContentRepository contentRepository) {

		this.drugRepository = drugRepository;
		this.drugManufacturerDaoService = drugManufacturerDaoService;
		this.drugGenericRepository = drugGenericRepository;
		this.doctorsInfoRepository = doctorsInfoRepository;
		this.doctorsSpecializaitonRepository = doctorsSpecializaitonRepository;
		this.contentRepository = contentRepository;
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

		newDrug.setId(0);
		newDrug.setDrugName("Captoril");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugName("Daptoril");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugName("Eaptoril");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugName("Faptoril");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugName("Gaptoril");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugName("Haptoril");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugName("Iaptoril");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugName("Japtoril");

		drugRepository.save(newDrug);

		DoctorsSpecialization specialization = new DoctorsSpecialization();
		specialization.setSpecializationName("PHYCIATRIST");
		doctorsSpecializaitonRepository.save(specialization);

		DoctorsInfo doctor = new DoctorsInfo();
		doctor.setDoctorName("Dr.Zahid Akbar");
		doctor.setChamber("i) Mirpur Diagonistic Center ii) Gulshan");
		doctor.setDoctorDetails("MBBS,FCPS");
		doctor.setDoctorsSpecialization(specialization);
		doctorsInfoRepository.save(doctor);

		for (int a = 0; a < 50; a++) {

			doctor.setDoctorName("Dr Iqbal " + a);
			doctor.setId(0);

			doctor.setChamber(
					"i) Rampura Diagonistic Center ii) Banani Tel: 76555222, Mobile: 01733400999, Fax: 65552323");
			doctor.setDoctorDetails(
					"MBBS,PGT,MBBS, FAEM (CMC, Vellore), FDP (George Washington University, USA), MEM (India), "
							+ "MMSc EM (Texila American University, Guyana), Examiner Royal College of Emergency Medicine (UK),"
							+ "	Consultant");
			doctorsInfoRepository.save(doctor);
		}
		for (int a = 0; a < 50; a++) {

			doctor.setDoctorName("Dr Alamgir Hossain " + a);
			doctor.setId(0);
			specialization.setIdSpecialization(1);
			doctor.setDoctorsSpecialization(specialization);
			doctor.setChamber(
					"i) Rampura Diagonistic Center ii) Banani Tel: 76555222, Mobile: 01733400999, Fax: 65552323");
			doctor.setDoctorDetails(
					"MBBS,PGT,MBBS, FAEM (CMC, Vellore), FDP (George Washington University, USA), MEM (India), "
							+ "MMSc EM (Texila American University, Guyana), Examiner Royal College of Emergency Medicine (UK),"
							+ "	Consultant");
			doctorsInfoRepository.save(doctor);
		}

		addNewsData();
		addAddData();

	}

	private void addAddData() {

		for (int a = 1; a <=6 ; a++) {

			Content add = new Content();
			add.setContentType("Advertisement");
			add.setContentPage("Index");
			add.setHeader("Advertisement Header " + a);
			add.setContent_summary("Advertisement Summary " + a);
			add.setAddSection("add"+a);
			
			add.setInsertDate(new Date());
			add.setExpireDate(new Date());

			contentRepository.save(add);
		}

	}

	private void addNewsData() {

		Content news1 = new Content();
		news1.setContentType("News");
		news1.setContentPage("Index");
		news1.setHeader("Health professionals: 4 Tips to deal with information fatigue");
		news1.setContent_summary("According to Oxford Dictionary, information fatigue is a condition of apathy, "
				+ "indifference or mental exhaustion due to being exposed "
				+ "to too much information. Information fatigue is also sometimes referred to as information overload");
		news1.setInsertDate(new Date());
		news1.setExpireDate(new Date());

		contentRepository.save(news1);

		Content news2 = new Content();
		news2.setContentType("News");
		news2.setContentPage("Index");
		news2.setHeader("Nobel Laureate Oliver Smithies leaves vast body of new work at age 91");
		news2.setContent_summary("Nobel laureate Oliver Smithies, the Weatherspoon Eminent Distinguished Professor,"
				+ " has passed on at the age of 91 on 10 January 2017. "
				+ "News of his death was reported by his wife, Dr. "
				+ "Nobuyo Maeda, to the school officials of University of North Carolina at Chapel Hill");
		news2.setInsertDate(new Date());
		news2.setExpireDate(new Date());

		contentRepository.save(news2);

	}

}
