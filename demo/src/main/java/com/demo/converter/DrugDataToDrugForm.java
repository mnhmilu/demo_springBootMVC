package com.demo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.demo.commands.DrugForm;
import com.demo.domain.Drug;

@Component
public class DrugDataToDrugForm implements Converter<Drug,DrugForm> {

	@Override
	public DrugForm	 convert(Drug data) {
	
		DrugForm frm = new DrugForm();
		frm.setId(data.getId());	
		frm.setDrugName(data.getDrugName());    
        frm.setGenericId(data.getDrugGeneric().getIdGeneric());
        frm.setGenericName(data.getDrugGeneric().getGenericName());  
        frm.setManufacturerId(data.getDrugManufacturer().getManufacturerId());
        frm.setManufacturer(data.getDrugManufacturer().getManufacturer());
        frm.setComposition(data.getComposition());
        frm.setContraindication(data.getContraindication());
        frm.setDosagesAdministration(data.getDosagesAdministration());
        frm.setDrugInteraction(data.getDrugInteraction());
        frm.setDrugprice(data.getDrugprice());
        frm.setIndication(data.getIndication());
        frm.setPregnancyLactation(data.getPregnancyLactation());
        frm.setSideEffect(data.getSideEffect());
        frm.setSpecialWarningPrecautions(data.getSpecialWarningPrecautions());
        frm.setStorage(data.getStorage());
        frm.setSideEffect(data.getSideEffect());    
        frm.setPackSize(data.getPackSize());   
        
		return frm;
	}

}
