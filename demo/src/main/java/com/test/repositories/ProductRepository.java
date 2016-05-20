package com.test.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	@Query("SELECT p FROM Product p WHERE lower(p.description) like lower(:productDescription)" )
	List<Product> getProductsbySearch(@Param("productDescription") String productDescription);
	
}
	
	