package com.demo.services;


import java.util.List;

import com.demo.domain.Product;

public interface ProductService {
    List<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);
    
    Iterable<Product> getProductsbySearch(String productDescription);
    
  
    
  
}
