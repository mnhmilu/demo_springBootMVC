package com.demo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.demo.commands.DrugGenericForm;
import com.demo.domain.DrugGeneric;

@Component
public class DrugGenericDataToDrugGenericForm implements Converter<DrugGeneric,DrugGenericForm> {

	@Override
	public DrugGenericForm	 convert(DrugGeneric data) {
	
		DrugGenericForm frm = new DrugGenericForm();
		
		frm.setAdvanceDrugReaction(data.getAdvanceDrugReaction());
		frm.setClassification(data.getClassification());
		frm.setContraindication(data.getContraindication());
		frm.setGenericName(data.getGenericName());
		frm.setIdGeneric(data.getIdGeneric());
		frm.setIndicationDosages(data.getIndicationDosages());
		frm.setInterAction(data.getInterAction());
		frm.setRemarks(data.getRemarks());
		frm.setSafetyRemarks(data.getSafetyRemarks());	
		frm.setSpecialPrecaution(data.getSpecialPrecaution());
		
		
		  
        
		return frm;
	}

}
