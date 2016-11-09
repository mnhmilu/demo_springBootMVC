package com.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Drug;
import com.demo.domain.DrugBrand;



@Repository
public interface DrugBrandRepository extends CrudRepository<DrugBrand, Integer>{

	
	
}
	
	