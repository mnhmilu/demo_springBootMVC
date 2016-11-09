package com.demo.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.demo.commands.DrugForm;
import com.demo.domain.Drug;

@Component
public class DrugFormToDrugData implements Converter<DrugForm,Drug> {

	@Override
	public Drug	 convert(DrugForm formData) {
	
		Drug entity = new Drug();
		entity.setId(formData.getId());
		entity.setBrandName(formData.getBrandName());
		entity.setDosages(formData.getDosages());
		entity.setDrugName(formData.getDrugName());
        entity.setGenericName(formData.getGenericName());
        entity.setInteraction(formData.getInteraction());
        entity.setOverView(formData.getOverView());
        entity.setProfessionals(formData.getProfessionals());
        entity.setSideEffects(formData.getSideEffects());
        entity.setInsertDate(new Date());		
		
		return entity;
	}

}
