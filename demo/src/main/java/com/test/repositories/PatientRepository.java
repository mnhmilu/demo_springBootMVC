package com.test.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.domain.PatientProfile;

@Repository
public interface PatientRepository extends CrudRepository<PatientProfile, Integer>{		
	//List findAllByOrderByDateAsc();
	List<PatientProfile> findTop50ByOrderByLastInsartedDateDesc();
	List<PatientProfile> findByMobileOrNameIgnoreCase(String mobile, String name);	
	PatientProfile findById(Integer id);
	
}
	
	