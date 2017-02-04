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
	@Query("select d from Drug d inner join d.drugManufacturer br inner join d.drugGeneric dg where br.manufacturerId=:manufacturerId or lower(dg.genericName) like lower(:genericName)  or lower(d.drugName) LIKE lower (:drugName)")
	List<Drug> findDrugByDrugManufacturerOrByDrugGenericOrDrugName(@Param("manufacturerId") Integer manufacturerId,@Param("genericName") String genericName,@Param("drugName") String drugName);	
	
	
	@Query("select d from Drug d inner join d.drugManufacturer br inner join d.drugGeneric dg where lower(dg.genericName) like lower( :genericName||'%') ")
	List<Drug> findDrugByDrugGeneric(@Param("genericName") String genericName);	
	
	@Query("select d from Drug d where lower(d.drugName) like lower( :drugName||'%') ")
	List<Drug> findDrugByDrugBrand(@Param("drugName") String drugName);		
	
	@Query("select d from Drug d inner join d.drugManufacturer br inner join d.drugGeneric dg where dg.idGeneric=:idgeneric order by d.drugprice DESC")
	List<Drug> findTop5ByDrugGeneric(@Param("idgeneric") Integer idgeneric);	
	
	
	
}
	
