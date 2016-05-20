package com.test.services;


import com.test.domain.Product;

public interface ProductService {
    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);
    
    Iterable<Product> getProductsbySearch(String productDescription);
    
  
    
  
}
