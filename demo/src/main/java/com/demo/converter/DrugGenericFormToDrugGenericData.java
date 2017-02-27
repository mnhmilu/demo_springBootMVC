package com.demo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.demo.commands.DrugGenericForm;
import com.demo.domain.DrugGeneric;

@Component
public class DrugGenericFormToDrugGenericData implements Converter<DrugGenericForm,DrugGeneric> {

	@Override
	public DrugGeneric	 convert(DrugGenericForm frm) {
	
		DrugGeneric data = new DrugGeneric();
		
		data.setAdvanceDrugReaction(frm.getAdvanceDrugReaction());
		data.setClassification(frm.getClassification());
		data.setContraindication(frm.getContraindication());
		data.setGenericName(frm.getGenericName());
		data.setIdGeneric(frm.getIdGeneric());
		data.setIndicationDosages(frm.getIndicationDosages());
		data.setInterAction(frm.getInterAction());
		data.setRemarks(frm.getRemarks());
		data.setSafetyRemarks(frm.getSafetyRemarks());		
		data.setSpecialPrecaution(frm.getSpecialPrecaution());
		  
		return data;
	}

}
