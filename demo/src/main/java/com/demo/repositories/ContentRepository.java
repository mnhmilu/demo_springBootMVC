package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.domain.Content;



@Repository
public interface ContentRepository extends CrudRepository<Content, Integer>{
	
	
	List<Content> findTop50ByOrderByInsertDateDesc();
	
	Content findById(Integer id);
	
	
	@Query("select d from Content d where lower(d.contentPage) like lower( :contentPage) ")
	List<Content> findContentByContentPageOrderByInsertDateDesc(@Param("contentPage") String contentPage);		
	
	
	
	
	//@Query("select d from Drug d inner join d.drugManufacturer br inner join d.drugGeneric dg where br.manufacturerId=:manufacturerId or lower(dg.genericName) like lower(:genericName)  or lower(d.drugName) LIKE lower (:drugName)")
	//List<Drug> findDrugByDrugManufacturerOrByDrugGenericOrDrugName(@Param("manufacturerId") Integer manufacturerId,@Param("genericName") String genericName,@Param("drugName") String drugName);	
		
	
	//@Query("select d from Drug d inner join d.drugManufacturer br inner join d.drugGeneric dg where lower(dg.genericName) like lower( :genericName||'%') ")
	//List<Drug> findDrugByDrugGeneric(@Param("genericName") String genericName);	
	
	//@Query("select d from Drug d where lower(d.drugName) like lower( :drugName||'%') ")
	//List<Drug> findDrugByDrugBrand(@Param("drugName") String drugName);		
	
	
	
}
	
	