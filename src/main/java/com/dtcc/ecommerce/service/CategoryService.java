package com.dtcc.ecommerce.service;

import com.dtcc.ecommerce.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
   // Category get(Category.class , long id);
    Category get(long id);
    List<Category> findAll();
    void saveCategory(Category category);
    /* List<Product> getAllProducts();
    void saveProduct(Product product);
    Product getProductById(long id);
    void deleteProductById(long id);*/
}
