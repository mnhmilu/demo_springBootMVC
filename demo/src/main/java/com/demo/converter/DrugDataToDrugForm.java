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
        frm.setDrugprice(data.getDrugprice());   
        frm.setStorage(data.getStorage());         
        frm.setPackSize(data.getPackSize());   
        frm.setDosageForm(data.getDosageForm());
        frm.setStrength(data.getStrength());
        
		return frm;
	}

}
