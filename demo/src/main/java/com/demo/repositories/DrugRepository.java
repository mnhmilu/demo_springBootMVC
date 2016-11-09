package com.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Drug;



@Repository
public interface DrugRepository extends CrudRepository<Drug, Integer>{
	
	List<Drug> findDrugByGenericNameOrBrandNameOrDrugName(String genericName, String brandName,String drugName);	
	List<Drug> findTop50ByOrderByInsertDateDesc();
	
	
}
	
	