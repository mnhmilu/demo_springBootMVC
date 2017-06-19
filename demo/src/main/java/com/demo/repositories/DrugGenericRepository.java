package com.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.domain.Drug;
import com.demo.domain.DrugGeneric;

@Repository
public interface DrugGenericRepository extends CrudRepository<DrugGeneric, Integer> {

	DrugGeneric findByIdGeneric(Integer id);

	Page<DrugGeneric> findDrugGenericByGenericNameIgnoreCase(@Param("genericName") String genericName,Pageable pageable);

	List<DrugGeneric> findTop50ByOrderByInsertDateDesc();

	Page<DrugGeneric> findAll(Pageable pageable);
	
	@Query("select d from DrugGeneric d where lower(trim(d.genericName)) like lower( :genericName||'%') ")
	Page<DrugGeneric> findDrugGenericByGenericName(@Param("genericName") String genericName,Pageable pageable);		
	
	

}
