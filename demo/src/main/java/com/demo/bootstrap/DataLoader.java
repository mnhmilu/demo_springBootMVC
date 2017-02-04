package com.demo.bootstrap;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import com.demo.domain.security.Role;
import com.demo.domain.security.User;
import com.demo.repositories.ContentRepository;
import com.demo.repositories.DoctorsInfoRepository;
import com.demo.repositories.DoctorsSpecializaitonRepository;
import com.demo.repositories.DrugGenericRepository;
import com.demo.repositories.DrugManufacturerRepository;
import com.demo.repositories.DrugRepository;
import com.demo.repositories.RoleRepository;
import com.demo.repositories.UserRepository;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private DrugManufacturerRepository drugManufacturerDaoService;

	private DrugGenericRepository drugGenericRepository;

	private DrugRepository drugRepository;

	private DoctorsInfoRepository doctorsInfoRepository;

	private DoctorsSpecializaitonRepository doctorsSpecializaitonRepository;

	private ContentRepository contentRepository;
	
	private UserRepository userRepository;
	
	private RoleRepository roleRepository;

	private Logger log = Logger.getLogger(DataLoader.class);

	@Autowired
	public void setProductRepository(DrugRepository drugRepository,
			DrugManufacturerRepository drugManufacturerDaoService, DrugGenericRepository drugGenericRepository,
			DoctorsInfoRepository doctorsInfoRepository,
			DoctorsSpecializaitonRepository doctorsSpecializaitonRepository, ContentRepository contentRepository,UserRepository userRepository,
			RoleRepository roleRepository) {

		this.drugRepository = drugRepository;
		this.drugManufacturerDaoService = drugManufacturerDaoService;
		this.drugGenericRepository = drugGenericRepository;
		this.doctorsInfoRepository = doctorsInfoRepository;
		this.doctorsSpecializaitonRepository = doctorsSpecializaitonRepository;
		this.contentRepository = contentRepository;
		this.userRepository = userRepository;
		this.roleRepository =roleRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		addDrugData();
		addNewsData();
		addAddData();
		addDrugUpdateData();
		addSecurityIntitalData();

	}
	
	private void addSecurityIntitalData(){			
		
		Role roleAdmin = new Role();
		roleAdmin.setRole("ADMIN");
		
		Role rolePharma = new Role();
		rolePharma.setRole("PHARMA");	
		
		Role roleManager = new Role();
		roleManager.setRole("MANAGER");
		
		Role roleDoctor = new Role();
		roleDoctor.setRole("DOCTOR");				
		
		//adding admin user
		Set<Role> setAdminRole = new HashSet<Role>();
		setAdminRole.add(roleAdmin);
		//setAdminRole.add(roleManager);
		
		User admin = new User();		
		admin.setPassword("pass");
		admin.setUsername("admin");	
		admin.setRoles(setAdminRole);		
		userRepository.save(admin);		
		
		
		
		//adding pharma
		Set<Role> setPharmaRole = new HashSet<Role>();
		setPharmaRole.add(rolePharma);		
		
		User pharma = new User();		
		pharma.setPassword("pharma");
		pharma.setUsername("pharma");	
		admin.setRoles(setPharmaRole);		
		userRepository.save(pharma);
		
		//adding manager user
		Set<Role> setManagerRole = new HashSet<Role>();
		setManagerRole.add(roleManager);		
		
		
		User userManager = new User();		
		userManager.setPassword("manager");
		userManager.setUsername("manager");			
		userManager.setRoles(setManagerRole);		
		userRepository.save(userManager);	
		
		
		//adding doctor user
		
		Set<Role> setDoctorRole = new HashSet<Role>();
		setDoctorRole.add(roleDoctor);		
		
		
		User userDoctor = new User();		
		userDoctor.setPassword("doctor");
		userDoctor.setUsername("doctor");			
		userDoctor.setRoles(setDoctorRole);		
		userRepository.save(userDoctor);
		
		
	
	}
	
	
	private void addDrugUpdateData(){		


			for (int a = 1; a <=6 ; a++) {

				Content add = new Content();
				add.setContentType("DrugUpdate");
				add.setContentPage("Index");
				add.setDrugUpdateType("ByBrand");
				add.setHeader("Arthritis Center" + a);
				add.setContent_summary("Arthritis is a condition associated with swelling and inflammation of the joints,"
						+ " which often results in pain and restriction of movement. It is estimated that more"
						+ " than 40 million people in America have some form of arthritis. Consult this center "
						+ "if you wish to find more information on the types of arthritis");
				add.setAddSection("add"+a);
				
				add.setInsertDate(new Date());
				add.setExpireDate(new Date());

				contentRepository.save(add);
			}
			
			for (int a = 1; a <=6 ; a++) {

				Content add = new Content();
				add.setContentType("DrugUpdate");
				add.setContentPage("Index");
				add.setDrugUpdateType("ByGeneric");
				add.setHeader("Micromedex® Consumer Information (Advanced)" + a);
				add.setContent_summary("Arthritis is a condition associated with swelling and inflammation of the joints,"
						+ " which often results in pain and restriction of movement. It is estimated that more"
						+ " than 40 million people in America have some form of arthritis. Consult this center "
						+ "if you wish to find more information on the types of arthritis");
				add.setAddSection("add"+a);
				
				add.setInsertDate(new Date());
				add.setExpireDate(new Date());

				contentRepository.save(add);
			}
			
			for (int a = 1; a <=6 ; a++) {

				Content add = new Content();
				add.setContentType("DrugUpdate");
				add.setContentPage("Index");
				add.setDrugUpdateType("ByNewMolecules");
				add.setHeader("AHFS DI Monographs" + a);
				add.setContent_summary("Arthritis is a condition associated with swelling and inflammation of the joints,"
						+ " which often results in pain and restriction of movement. It is estimated that more"
						+ " than 40 million people in America have some form of arthritis. Consult this center "
						+ "if you wish to find more information on the types of arthritis");
				add.setAddSection("add"+a);
				
				add.setInsertDate(new Date());
				add.setExpireDate(new Date());

				contentRepository.save(add);
			}


		
		
		
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
	
	
	private void addDrugData() {
		
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
		newDrug.setDrugprice(58.87);
		newDrug.setDrugName("Captoril");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugprice(60.87);
		newDrug.setDrugName("Daptoril");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugprice(62.87);
		newDrug.setDrugName("Eaptoril");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugprice(64.87);
		newDrug.setDrugName("Faptoril");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugprice(66.87);
		newDrug.setDrugName("Gaptoril");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugprice(78.87);
		newDrug.setDrugName("Haptoril");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugprice(56.87);
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
			specialization.setIdSpecialization(specialization.getIdSpecialization());
			doctor.setDoctorsSpecialization(specialization);
			doctor.setChamber(
					"i) Rampura Diagonistic Center ii) Banani Tel: 76555222, Mobile: 01733400999, Fax: 65552323");
			doctor.setDoctorDetails(
					"MBBS,PGT,MBBS, FAEM (CMC, Vellore), FDP (George Washington University, USA), MEM (India), "
							+ "MMSc EM (Texila American University, Guyana), Examiner Royal College of Emergency Medicine (UK),"
							+ "	Consultant");
			doctorsInfoRepository.save(doctor);
		}
		
		
	}

}
