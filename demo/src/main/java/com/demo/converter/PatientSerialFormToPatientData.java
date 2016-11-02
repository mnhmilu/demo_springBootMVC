package com.demo.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.demo.commands.PatientSerialForm;
import com.demo.domain.PatientProfile;

@Component
public class PatientSerialFormToPatientData implements Converter<PatientSerialForm,PatientProfile> {

	@Override
	public PatientProfile convert(PatientSerialForm source) {
	
		PatientProfile patient = new PatientProfile();
		patient.setId(source.getId());
		patient.setAge(source.getAge());
		patient.setName(source.getName());
		patient.setMobile(source.getMobile());
		patient.setLastInsartedDate(new Date());
		
		return patient;
	}

}
