package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.demo.domain.Drug;
import com.demo.domain.DrugBrand;
import com.demo.domain.DrugGeneric;
import com.demo.domain.PatientProfile;



@Repository
public interface DrugRepository extends CrudRepository<Drug, Integer>{
	
	//List<Drug> findDrugByDrugGenericOrDrugBrandOrDrugName( DrugGeneric generic,DrugBrand brand,String drugName);	
	List<Drug> findTop50ByOrderByInsertDateDesc();
	Drug findById(Integer id);
	
	//@Query("select u.userName from User u inner join u.area ar where ar.idArea = :idArea")
	
	@Query("select d from Drug d inner join d.drugBrand br inner join d.drugGeneric dg where br.idBrand=:idBrand or dg.idGeneric=:idGeneric or lower(d.drugName) LIKE lower (:drugName)")
	List<Drug> findDrugByDrugBrandOrByDrugGenericOrDrugName(@Param("idBrand") Integer idBrand,@Param("idGeneric") Integer idGeneric,@Param("drugName") String drugName);	
	
	
}
	
	