package com.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Drug;
import com.demo.domain.DrugBrand;
import com.demo.domain.DrugGeneric;
import com.demo.domain.PatientProfile;



@Repository
public interface DrugRepository extends CrudRepository<Drug, Integer>{
	
	List<Drug> findDrugByDrugGenericOrDrugBrandOrDrugNameContainingIgnoreCase( DrugGeneric generic,DrugBrand brand,String drugName);	
	List<Drug> findTop50ByOrderByInsertDateDesc();
	Drug findById(Integer id);
	
	
}
	
	