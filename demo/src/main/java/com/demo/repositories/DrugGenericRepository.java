package com.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.domain.DrugGeneric;

@Repository
public interface DrugGenericRepository extends CrudRepository<DrugGeneric, Integer> {

	DrugGeneric findByIdGeneric(Integer id);

	List<DrugGeneric> findDrugGenericByGenericNameIgnoreCase(@Param("genericName") String genericName);

	List<DrugGeneric> findTop50ByOrderByInsertDateDesc();

	Page<DrugGeneric> findAll(Pageable pageable);

}
