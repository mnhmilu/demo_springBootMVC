package com.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import com.demo.domain.Content;



@Repository
public interface ContentRepository extends CrudRepository<Content, Integer>{
	
	
	List<Content> findTop50ByOrderByInsertDateDesc();
	
	Content findById(Integer id);
	
	
	@Query("select d from Content d where lower(d.contentPage) like lower( :contentPage) ")
	List<Content> findContentByContentPageOrderByInsertDateDesc(@Param("contentPage") String contentPage);		
	
	@Query("select d from Content d where lower(d.contentType) like lower( :contentType) or lower(d.header) like lower('%' ||:header||'%') ")
	Page<Content> findContentByContentTypeOrByHeaderOrderByInsertDateDesc(@Param("contentType") String contentType,@Param("header") String header,Pageable pageable);	
	

	
	
}
	
	