package com.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.controller.PatientController;
import com.demo.domain.PatientProfile;
import com.demo.domain.PatientSerials;
import com.demo.dto.PatientSerialDTO;
import com.demo.repositories.PatientRepository;
import com.demo.repositories.PatientSerialRepository;

@Service
public class PatientServiceImpl implements PatientProfiletService {
	
	private final Logger slf4jLogger = LoggerFactory.getLogger(PatientController.class);
	

	private PatientSerialRepository patientSerialRepository;
	private PatientRepository patientRepository;

	@Autowired
	public void setPatientRepository(PatientRepository patientRepository,
			PatientSerialRepository patientSerialRepository) {

		this.patientSerialRepository = patientSerialRepository;
		this.patientRepository = patientRepository;
	}

	@Override
	public void savePatientSerial(PatientSerials patientSerial) {
		
		slf4jLogger.info("PatientServiceImpl::savePatientSerial started with param  patient profile id {0}"+new Object[]{patientSerial.getPatientProfile().getId()});
		

		List<PatientSerials> list = patientSerialRepository.findBySerialDate(patientSerial.getSerialDate());

		patientSerial.setSerialNumber(list.size() + 1);

		patientSerialRepository.save(patientSerial);
		
		slf4jLogger.info("PatientServiceImpl::savePatientSerial call ended");

	}

	@Override
	public void savePatientInfoWithSerail(PatientSerialDTO dto) {

		PatientProfile patient = new PatientProfile();
		patient.setId(dto.getId());
		patient.setAge(dto.getAge());
		patient.setName(dto.getName());
		patient.setMobile(dto.getMobile());
		patient.setLastInsartedDate(new Date());
		patientRepository.save(patient);

		if (dto.getId() == null) {

			List<PatientSerials> list = patientSerialRepository.findBySerialDate(dto.getSerialDate());

			PatientSerials oneSerial = new PatientSerials();
			oneSerial.setLastInsartedDate(new Date());
			oneSerial.setRemarks("test");
			oneSerial.setSerialNumber(list.size() + 1);
			oneSerial.setPatientProfile(patient);
			oneSerial.setSerialDate(dto.getSerialDate());
			patientSerialRepository.save(oneSerial);
		}

	}

	@Override
	public List<PatientSerialDTO> searchPatientSerialInformationbyDate(Date searchDate) {

		List<PatientSerialDTO> resultList = new ArrayList<PatientSerialDTO>();

		List<PatientSerials> list = patientSerialRepository.findBySerialDate(searchDate);

		int counter = 1;

		for (PatientSerials item : list) {
			PatientSerialDTO dto = new PatientSerialDTO();
			dto.setItem_no(counter);
			dto.setName(item.getPatientProfile().getName());
			dto.setMobile(item.getPatientProfile().getMobile());
			dto.setSerialDate(item.getSerialDate());
			dto.setSerialNumber(item.getSerialNumber());
			dto.setRemarks(item.getRemarks());
			dto.setAge(item.getPatientProfile().getAge());
			resultList.add(dto);
			counter++;
		}

		return resultList;
	}
}
