package com.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.domain.Product;
import com.test.services.ProductService;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("products", productService.listAllProducts());
        return "products";
    }

    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "productshow";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "productform";
    }

    @RequestMapping("product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "productform";
    }
    
    @RequestMapping("product/search")
    public String searchProduct(Model model){
        model.addAttribute("product", new Product());
        return "productSearch";
    }
    @RequestMapping(value = "productSearchResult", method = RequestMethod.POST)
    public String productSearchResult(Product product, Model model){    	
    	
    	 model.addAttribute("products", productService.getProductsbySearch(product.getDescription()));
         return "products";
      
    }
    

    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(@Valid Product product, BindingResult bindingResult){
    	
    	if(bindingResult.hasErrors())
    	{
    		 return "productform";    		
    	}
        productService.saveProduct(product);
        return "redirect:/products";
      
    }

    @RequestMapping("product/delete/{id}")
    public String delete(@PathVariable Integer id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }
    


}
