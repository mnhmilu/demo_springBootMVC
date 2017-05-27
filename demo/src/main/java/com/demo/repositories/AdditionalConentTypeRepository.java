package com.demo.repositories;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.AdditionalContentType;


/**
 * @author ekansh
 * @since 2/4/16
 */
public interface AdditionalConentTypeRepository extends JpaRepository<AdditionalContentType,Long> {
	
	List<AdditionalContentType> findAllByOrderByAdditionalContentTypeNameAsc();

	
}
