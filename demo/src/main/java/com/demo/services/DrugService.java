package com.demo.services;


import java.util.List;

import com.demo.commands.DrugForm;
import com.demo.domain.Drug;
import com.demo.domain.Product;

public interface DrugService {
	
    List<Drug> listAllProducts();

    Drug getProductById(Integer id);

    Drug saveProduct(Product product);

    void deleteProduct(Integer id);
       
    List<DrugForm> getDrugByNameOrGenericOrBrand(String drugName, String genericName,String brand);
    
  
    
  
}
