package com.demo.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.demo.commands.DrugForm;
import com.demo.domain.Drug;
import com.demo.domain.DrugBrand;
import com.demo.domain.DrugGeneric;

@Component
public class DrugFormToDrugData implements Converter<DrugForm,Drug> {

	@Override
	public Drug	 convert(DrugForm formData) {
	
		Drug entity = new Drug();
		entity.setId(formData.getId());		
		entity.setDosages(formData.getDosages());
		entity.setDrugName(formData.getDrugName());       
        entity.setInteraction(formData.getInteraction());
        entity.setOverView(formData.getOverView());
        entity.setProfessionals(formData.getProfessionals());
        entity.setSideEffects(formData.getSideEffects());
        entity.setInsertDate(new Date());		
        
        DrugBrand brand = new DrugBrand();
        brand.setIdBrand(formData.getBrandId());
        entity.setDrugBrand(brand);
        
        DrugGeneric generic = new DrugGeneric();
        generic.setId(formData.getGenericId());
        entity.setDrugGeneric(generic);      
		
		return entity;
	}

}
