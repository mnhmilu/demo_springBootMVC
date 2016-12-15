package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.domain.DoctorsInfo;
import com.demo.domain.Drug;



@Repository
public interface DoctorsInfoRepository extends CrudRepository<DoctorsInfo, Integer>{
	
	
	List<DoctorsInfo> findTop50ByOrderByInsertDateDesc();
	
	DoctorsInfo findById(Integer id);
		
	
	@Query("select d from DoctorsInfo d inner join d.doctorsSpecialization ds  where lower(d.doctorsSpecialization) like lower( :doctorsSpecialization||'%') ")
	List<Drug> findDoctorsInfoByDoctorsSpecialization(@Param("doctorsSpecialization") String doctorsSpecialization);	
	
	
	
}
	
	