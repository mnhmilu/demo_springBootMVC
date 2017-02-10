package com.demo.services;


import java.util.List;

import com.demo.commands.DrugForm;
import com.demo.domain.Drug;

public interface DrugService {
	
    List<Drug> listAllProducts();


       
    List<DrugForm> getDrugByNameOrGenericOrBrand(String drugName, String genericName,String brand);
    
  
    
  
}
