package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.DrugManufacturer;



@Repository
public interface DrugManufacturerRepository extends CrudRepository<DrugManufacturer, Integer>{

	
	
}
	
	