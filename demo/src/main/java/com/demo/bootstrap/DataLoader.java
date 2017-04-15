package com.demo.bootstrap;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private Environment environment;

	@Autowired
	public void setProductRepository(DrugRepository drugRepository,
			DrugManufacturerRepository drugManufacturerDaoService, DrugGenericRepository drugGenericRepository,
			DoctorsInfoRepository doctorsInfoRepository,
			DoctorsSpecializaitonRepository doctorsSpecializaitonRepository, ContentRepository contentRepository,
			UserRepository userRepository, RoleRepository roleRepository) {

		this.drugRepository = drugRepository;
		this.drugManufacturerDaoService = drugManufacturerDaoService;
		this.drugGenericRepository = drugGenericRepository;
		this.doctorsInfoRepository = doctorsInfoRepository;
		this.doctorsSpecializaitonRepository = doctorsSpecializaitonRepository;
		this.contentRepository = contentRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (Arrays.stream(environment.getActiveProfiles()).anyMatch(env -> (env.equalsIgnoreCase("dev")))) {

			addGenericData();
			addDrugData();
			addNewsData();
			addAddData();
			addDrugUpdateData();
			addSecurityIntitalData();
		}

	}

	private void addGenericData() {

		DrugGeneric generic = new DrugGeneric();
		generic.setGenericName("ETODOLAC");
		generic.setAdvanceDrugReaction("Sample advance Drug Reaction");
		generic.setClassification("Sample Classification");
		generic.setContraindication("Sample ContraIndication");
		generic.setIndicationDosages("Sample Dosage and Indication");
		generic.setInsertDate(new Date());
		generic.setInterAction("Sample Interaction");
		generic.setRemarks("Sample Remarks");
		generic.setSafetyRemarks("PX LX Food Lab");
		generic.setSpecialPrecaution("Sample Special Precaution");
		drugGenericRepository.save(generic);

	}

	private void addSecurityIntitalData() {

		Role roleAdmin = new Role();
		roleAdmin.setRole("ADMIN");

		Role rolePharma = new Role();
		rolePharma.setRole("PHARMA");

		Role roleManager = new Role();
		roleManager.setRole("MANAGER");

		Role roleDoctor = new Role();
		roleDoctor.setRole("DOCTOR");

		// adding admin user
		Set<Role> setAdminRole = new HashSet<Role>();
		setAdminRole.add(roleAdmin);
		// setAdminRole.add(roleManager);

		User admin = new User();
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode("java8");	
		admin.setPassword(hashedPassword);
		admin.setUsername("admin");
		admin.setRoles(setAdminRole);
		userRepository.save(admin);

		// adding pharma
		Set<Role> setPharmaRole = new HashSet<Role>();
		setPharmaRole.add(rolePharma);

		User pharma = new User();
		hashedPassword = passwordEncoder.encode("pharma8");
		pharma.setPassword(hashedPassword);
		pharma.setUsername("pharma");
		admin.setRoles(setPharmaRole);
		userRepository.save(pharma);

		// adding manager user
		Set<Role> setManagerRole = new HashSet<Role>();
		setManagerRole.add(roleManager);

		User userManager = new User();
		hashedPassword = passwordEncoder.encode("manager8");
		userManager.setPassword(hashedPassword);
		userManager.setUsername("manager");
		userManager.setRoles(setManagerRole);
		userRepository.save(userManager);

		// adding doctor user

		Set<Role> setDoctorRole = new HashSet<Role>();
		setDoctorRole.add(roleDoctor);

		User userDoctor = new User();
		hashedPassword = passwordEncoder.encode("doctor8");
		userDoctor.setPassword(hashedPassword);
		userDoctor.setUsername("doctor");
		userDoctor.setRoles(setDoctorRole);
		userRepository.save(userDoctor);

	}

	private void addDrugUpdateData() {

		for (int a = 1; a <= 3; a++) {

			Content add = new Content();
			add.setContentType("DrugUpdate");
			add.setContentPage("Index");
			add.setDrugUpdateType("ByBrand");
			add.setHeader("Arthritis Center" + a);
			add.setContent_summary("Arthritis is a condition associated with swelling and inflammation of the joints,"
					+ " which often results in pain and restriction of movement. It is estimated that more"
					+ " than 40 million people in America have some form of arthritis. Consult this center "
					+ "if you wish to find more information on the types of arthritis");
			add.setAddSection("add" + a);

			add.setInsertDate(new Date());
			add.setExpireDate(new Date());

			contentRepository.save(add);
		}

		for (int a = 1; a <= 46; a++) {

			Content add = new Content();
			add.setContentType("DrugUpdate");
			add.setContentPage("Index");
			add.setDrugUpdateType("ByGeneric");
			add.setHeader("Micromedex® Consumer Information (Advanced)" + a);
			add.setContent_summary("Arthritis is a condition associated with swelling and inflammation of the joints,"
					+ " which often results in pain and restriction of movement. It is estimated that more"
					+ " than 40 million people in America have some form of arthritis. Consult this center "
					+ "if you wish to find more information on the types of arthritis");
			add.setAddSection("add" + a);

			add.setInsertDate(new Date());
			add.setExpireDate(new Date());

			contentRepository.save(add);
		}

		for (int a = 1; a <= 46; a++) {

			Content add = new Content();
			add.setContentType("DrugUpdate");
			add.setContentPage("Index");
			add.setDrugUpdateType("ByNewMolecules");
			add.setHeader("AHFS DI Monographs" + a);
			add.setContent_summary("Arthritis is a condition associated with swelling and inflammation of the joints,"
					+ " which often results in pain and restriction of movement. It is estimated that more"
					+ " than 40 million people in America have some form of arthritis. Consult this center "
					+ "if you wish to find more information on the types of arthritis");
			add.setAddSection("add" + a);

			add.setInsertDate(new Date());
			add.setExpireDate(new Date());

			contentRepository.save(add);
		}

	}

	private void addAddData() {

		for (int a = 1; a <= 46; a++) {

			Content add = new Content();
			add.setContentType("Advertisement");
			add.setContentPage("Index");
			add.setHeader("Advertisement Header " + a);
			add.setContent_summary("Advertisement Summary " + a);
			add.setAddSection("add" + a);

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
		manufacturer.setManufacturer("ACME Lab");
		drugManufacturerDaoService.save(manufacturer);

		DrugManufacturer manufacturer2 = new DrugManufacturer();
		manufacturer2.setManufacturer("Novartis");
		drugManufacturerDaoService.save(manufacturer2);

		DrugGeneric generic = new DrugGeneric();
		generic.setGenericName("DICLOFENAC");
		generic.setClassification("Nonsteriodal Anti-Inflammatory Drug");
		generic.setSafetyRemarks("PX LC Food");

		generic.setIndicationDosages("sprains; Strains; Tendinitis; Pain "
				+ "& inflammation associated w/ musculoskeletal & joint disorders; Bursitis; Acute gout; Dysmenorrhoea: Adult: As sodium: 75-150 mg daily"
				+ "in divided doses. Max: 150 mg/day. Migraine: Adult: As potassium: Initially, 50 mg taken at the 1st sign of an attack, an additional dose"
				+ " of 50 mg may be taken after 2 hr if symptoms persist. If needed, further doses of 50 mg may be taken every 4-6 hr. Max: 200 mg/day."
				+ "NTRAMUSCULAR " + "Sprains; Strains; Tendinitis;Pain "
				+ "& inflammation associated w/ musculoskeletal & joint disorders; Bursitis; Acute gout; Dysmenorrhoea: Adult: As sodium: 75 mg once daily, injected into the gluteal muscle,"
				+ "may increase to 75 mg bid in severe conditions. "
				+ "Renal colic: Adult: As sodium: 75 mg, may repeat once after 30 mins if needed. Max: 150 mg/day. "
				+ "INTRAVENOUS "
				+ "Postoperative pain: Adult: As sodium: 75 mg infusion in 5% glucose or 0.9% Na CI given over 30-120 minutes, may repeat after 4-6 hr if necessary. "
				+ "WWWIVUVWlVUU-.‘-v"
				+ "Prophylaxis of postoperative pain: l Adult: As sodium: 25-50 mg infusion given ‘ after surgery over 15-60 mins followed by "
				+ "5 mg/hr. Max: 150 mg daily. " + "RECTAL "
				+ "Postoperative pain: Adult: 75-150 mg daily, in divided doses (25 mg, 50 mg & "
				+ "100 mg supp only). Max: 150 mg/day (inclusive of diclofenac administered through other routes). Child: 6-1 2 yr: 1-2 mg/kg/day in divided doses (12.5 mg & 25 mg supp only) for max of 4 days. ");

		generic.setAdvanceDrugReaction(
				"Gl disturbances; headache, dizziness, rash; GI bleeding, peptic ulceration; abnormalities of "
						+ "kidney function. Pain & tissue damage at lnj site (lM); local irritation (rectal); "
						+ "transient burning & stinging (ophthalmic). StevensJohnson syndrome, "
						+ "exfoliative dermatitis toxic epidermal necrolysis. k ");
		generic.setContraindication(
				"Active peptic ulcer; hypersensitivity to diclofenac or other NSAle. Treatment of perioperative pain in CABG surgery. "
						+ "3rd trimester of pregnancy. Topical: Not to be applied onto damaged or nonintact skin");
		generic.setSpecialPrecaution(
				"History of GI ulceration; impaired cardiac, renal or hepatic function; hypertension; lactation. "
						+ "lV admin in patients w/ moderate or severe renal impairment; hypovolaemia or dehydration; asthma, porphyria. Monitor LFTs in patients on prolonged therapy. May prolong bleeding time; caution when used in patients M coagulation disorders or on anticoagulants."
						+ " Prolonged therapy may increase risk of anaemia. fst & 2nd trimester of pregnancy. Elderly, debilitated patients.");
		generic.setInterAction("Not to be given IV to patients who are receiving other NSAle or anticoagulants including low dose heparin."
				+ " Renal function may be worsened when used w/ ciclosporin or triamterene. Altered absorption when given w/ sucralfate, "
				+ "colestyramine or colestipol. Ophthalmic application of diclofenac may reduce the efficacy of ophth acetylcholine & carbachol. Increased risk of GI ulceration , "
				+ "& bleeding when used w/ corticosteroids, aspirin or anticoagulants. increases blood levels of digoxin, lithium & methotrexate. Potentiate potassium-sparing diuretics.");
		
		

		drugGenericRepository.save(generic);

		Drug newDrug = new Drug();

		newDrug.setDrugManufacturer(manufacturer);
		newDrug.setDrugGeneric(generic);
		newDrug.setDrugName("A-FENAC");
		newDrug.setDosageForm("TAB");
		newDrug.setStrength("25mg");
		

		newDrug.setInsertDate(new Date());
		newDrug.setDrugprice(55.00);
		newDrug.setPackSize("10X10 size");

		drugRepository.save(newDrug);

		newDrug.setId(0);
		newDrug.setDrugName("A-FENAC");
		newDrug.setDosageForm("TAB");
		newDrug.setStrength("50mg");
		

		newDrug.setInsertDate(new Date());
		newDrug.setDrugprice(84.00);
		newDrug.setPackSize("10X10 size");

		drugRepository.save(newDrug);
		
		
		newDrug.setId(0);
		newDrug.setDrugManufacturer(manufacturer2);
		newDrug.setDrugName("CATAFLAM");
		newDrug.setDosageForm("TAB");
		newDrug.setStrength("25mg");
		

		newDrug.setInsertDate(new Date());
		newDrug.setDrugprice(0.00);
		newDrug.setPackSize("50's ");

		drugRepository.save(newDrug);
		
		newDrug.setId(0);
		newDrug.setDrugManufacturer(manufacturer2);
		newDrug.setDrugName("CATAFLAM");
		newDrug.setDosageForm("TAB");
		newDrug.setStrength("50mg");
		

		newDrug.setInsertDate(new Date());
		newDrug.setDrugprice(0.00);
		newDrug.setPackSize("50's ");

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

		

	}

}
