package com.dtcc.ecommerce.controller;

import com.dtcc.ecommerce.model.Category;
import com.dtcc.ecommerce.model.Product;
import com.dtcc.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String viewCategoriesPage(Model model){
        model.addAttribute("listCategories",categoryService.findAll());
        return "all_categories";
    }

    @GetMapping("/showNewCategoryForm")
    public String showNewProductForm(Model model){
        model.addAttribute("category",new Category());
        return "add_category";
    }

    @PostMapping("/saveCategory")  //for saving and update
    public String saveProduct( @ModelAttribute("category") Category category){
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }
}
