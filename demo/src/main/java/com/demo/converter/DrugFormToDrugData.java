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
		entity.setDrugprice(formData.getDrugprice());
		entity.setStorage(formData.getStorage());		
        entity.setInsertDate(new Date());		
        entity.setPackSize(formData.getPackSize());       
        entity.setDosageForm(formData.getDosageForm());
        entity.setStrength(formData.getStrength());
        DrugManufacturer manufacturer = new DrugManufacturer();
        manufacturer.setManufacturerId(formData.getManufacturerId());
        entity.setDrugManufacturer(manufacturer);        
        DrugGeneric generic = new DrugGeneric();
        generic.setIdGeneric(formData.getGenericId());
        entity.setDrugGeneric(generic);   
        entity.setImage(formData.getImage());
		
		return entity;
	}

}
