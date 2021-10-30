package com.dtcc.ecommerce.service;

import com.dtcc.ecommerce.model.Category;
import com.dtcc.ecommerce.model.Product;
import com.dtcc.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.getById(id);
    }

    @Override
    public void deleteProductById(long id) {
         productRepository.deleteById(id);
    }

}
