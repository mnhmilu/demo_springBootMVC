package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.domain.Drug;



@Repository
public interface DrugRepository extends CrudRepository<Drug, Integer>{
	
	//List<Drug> findDrugByDrugGenericOrDrugBrandOrDrugName( DrugGeneric generic,DrugBrand brand,String drugName);	
	List<Drug> findTop50ByOrderByInsertDateDesc();
	Drug findById(Integer id);
	
	//@Query("select u.userName from User u inner join u.area ar where ar.idArea = :idArea")
	
	@Query("select d from Drug d inner join d.drugManufacturer br inner join d.drugGeneric dg where br.manufacturerId=:manufacturerId or dg.idGeneric=:idGeneric or lower(d.drugName) LIKE lower (:drugName)")
	List<Drug> findDrugByDrugManufacturerOrByDrugGenericOrDrugName(@Param("manufacturerId") Integer manufacturerId,@Param("idGeneric") Integer idGeneric,@Param("drugName") String drugName);	
	
	
}
	
	