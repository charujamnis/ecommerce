package com.dtcc.ecommerce.service;

import com.dtcc.ecommerce.model.Category;
import com.dtcc.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product);
    Product getProductById(long id);
    void deleteProductById(long id);
}
