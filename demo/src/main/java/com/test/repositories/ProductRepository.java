package com.test.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.test.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
}
