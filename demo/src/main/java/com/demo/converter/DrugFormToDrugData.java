package com.demo.converter;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.demo.commands.DrugForm;
import com.demo.domain.Drug;
import com.demo.domain.DrugGeneric;
import com.demo.domain.DrugManufacturer;

@Component
public class DrugFormToDrugData implements Converter<DrugForm,Drug> {

	@Override
	public Drug	 convert(DrugForm formData) {
	
		Drug entity = new Drug();
		entity.setId(formData.getId());		
		
		entity.setDrugName(formData.getDrugName());
		entity.setComposition(formData.getComposition());
		entity.setContraindication(formData.getContraindication());
		entity.setDosagesAdministration(formData.getDosagesAdministration());
		entity.setDrugInteraction(formData.getDrugInteraction());
		entity.setDrugprice(formData.getDrugprice());
		entity.setIndication(formData.getIndication());
		entity.setPregnancyLactation(formData.getPregnancyLactation());
		entity.setSideEffect(formData.getSideEffect());
		entity.setSpecialWarningPrecautions(formData.getSpecialWarningPrecautions());
		entity.setStorage(formData.getStorage());		
        entity.setInsertDate(new Date());		
        
        DrugManufacturer manufacturer = new DrugManufacturer();
        manufacturer.setManufacturerId(formData.getManufacturerId());
        entity.setDrugManufacturer(manufacturer);
        
        DrugGeneric generic = new DrugGeneric();
        generic.setIdGeneric(formData.getGenericId());
        entity.setDrugGeneric(generic);      
		
		return entity;
	}

}
