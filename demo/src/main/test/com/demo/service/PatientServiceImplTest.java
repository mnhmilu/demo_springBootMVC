package com.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import scala.annotation.meta.setter;

import com.test.domain.PatientProfile;
import com.test.domain.PatientSerials;
import com.test.repositories.PatientRepository;
import com.test.repositories.PatientSerialRepository;
import com.test.services.PatientServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PatientServiceImplTest {

	@InjectMocks
	private PatientServiceImpl patientServiceImpl;

	@Mock
	private PatientSerialRepository patientSerialRepository;

	@Mock
	private PatientRepository patientRepository;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void savePatientSerialTestWithIntialSizeOne() {

		List<PatientSerials> serials = new ArrayList<>();
		PatientSerials serialOne = new PatientSerials();
		serialOne.setSerialNumber(1);

		PatientProfile patientOne = new PatientProfile();

		patientOne.setName("Nahid");
		patientOne.setAge(35);
		patientOne.setMobile("01733400896");
		patientOne.setId(1);

		PatientSerials patientSerial = new PatientSerials();
		patientSerial.setPatientProfile(patientOne);
		patientSerial.setLastInsartedDate(new Date());
		patientSerial.setSerialDate(new Date());

		serials.add(serialOne);

		//when initial list is one then expected serial should two
		
		when(patientSerialRepository.findBySerialDate(new Date())).thenReturn(
				(List) serials);

		patientServiceImpl.savePatientSerial(patientSerial);

		assertEquals(Integer.valueOf(2), patientSerial.getSerialNumber());

		//when initial list is zero then expected serial should one
		
		List<PatientSerials> emptySerials = new ArrayList<>();
		
		when(patientSerialRepository.findBySerialDate(new Date())).thenReturn(
				(List) emptySerials);

		patientServiceImpl.savePatientSerial(patientSerial);

		assertEquals(Integer.valueOf(1), patientSerial.getSerialNumber());		
		

	}
	
	
	
	@Test
	public void savePatientSerialTestWithIntialSizeZero() {

		List<PatientSerials> serials = new ArrayList<>();
		

		PatientProfile patientOne = new PatientProfile();

		patientOne.setName("Nahid");
		patientOne.setAge(35);
		patientOne.setMobile("01733400896");
		patientOne.setId(1);

		PatientSerials patientSerial = new PatientSerials();
		patientSerial.setPatientProfile(patientOne);
		patientSerial.setLastInsartedDate(new Date());
		patientSerial.setSerialDate(new Date());

		

		//when initial list is zero then expected serial should one
		
		List<PatientSerials> emptySerials = new ArrayList<>();
		
		when(patientSerialRepository.findBySerialDate(new Date())).thenReturn(
				(List) serials);

		patientServiceImpl.savePatientSerial(patientSerial);

		assertEquals(Integer.valueOf(1), patientSerial.getSerialNumber());		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
