package com.dtcc.ecommerce.controller;

import com.dtcc.ecommerce.model.Category;
import com.dtcc.ecommerce.model.Product;
import com.dtcc.ecommerce.service.CategoryService;
import com.dtcc.ecommerce.service.ProductService;
import com.dtcc.ecommerce.utility.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;



    @GetMapping("/products")
    public String viewProductsPage(Model model){
        model.addAttribute("listProducts",productService.getAllProducts());
        return "all_products";
    }

    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model){
        model.addAttribute("product",new Product());
        model.addAttribute("category1",new Category());
        model.addAttribute("categories",categoryService.findAll());
       // model.addAttribute("category",new Category());
        return "add_product";
    }

    @PostMapping("/saveProduct")  //for saving and update
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, @RequestParam("image1") MultipartFile multipartFile) throws IOException {
        // String fileName = multipartFile.getOriginalFilename();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println("The file name is "+fileName);
        product.setPhoto(fileName);
        String uploadDir = "product-photos/" + product.getCategory().getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        long cId=product.getCategory().getId();
        Category category=categoryService.get(cId);
        product.setCategory(category);
        productService.saveProduct(product);

        return "redirect:/products";
    }

    @GetMapping("/showUpdateProductForm/{id}")
    public String showUpdateProductForm(@PathVariable(value="id") long id, Model model){
        Product product=productService.getProductById(id);
        System.out.println("product id os "+product.getId());
        model.addAttribute("product",product);
        model.addAttribute("categories",categoryService.findAll());
        return "updateProductForm";
    }

    @PostMapping("/updateProduct")  //for saving and update
    public String updateProduct( @ModelAttribute("product") Product product){
        long cId=product.getCategory().getId();
        Category category=categoryService.get(cId);
        product.setCategory(category);
        productService.saveProduct(product);
        return "redirect:/";
    }
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value="id") long id){
        productService.deleteProductById(id);
        return "redirect:/";
    }
}

/*
https://www.codejava.net/frameworks/spring-boot/spring-boot-file-upload-tutorial
* */
