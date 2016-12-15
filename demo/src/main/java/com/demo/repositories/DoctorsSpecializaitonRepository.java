package com.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.domain.DoctorsSpecialization;

@Repository
public interface DoctorsSpecializaitonRepository extends CrudRepository<DoctorsSpecialization, Integer>{

	DoctorsSpecialization findByIdSpecialization(Integer id);
	List<DoctorsSpecialization> findTop500ByOrderByInsertDateDesc();
	
}
	
		