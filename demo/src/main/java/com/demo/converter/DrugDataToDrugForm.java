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
		frm.setDosages(data.getDosages());
		frm.setDrugName(data.getDrugName());       
        frm.setInteraction(data.getInteraction());
        frm.setOverView(data.getOverView());
        frm.setProfessionals(data.getProfessionals());
        frm.setSideEffects(data.getSideEffects());
        
        frm.setBrandId(data.getDrugBrand().getIdBrand());
        frm.setBrandName(data.getDrugBrand().getBrandName());
        
        frm.setGenericId(data.getDrugGeneric().getIdGeneric());
        frm.setGenericName(data.getDrugGeneric().getGenericName());  
        
        
		return frm;
	}

}
