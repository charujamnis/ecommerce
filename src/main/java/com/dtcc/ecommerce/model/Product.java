package com.dtcc.ecommerce.model;

import com.dtcc.ecommerce.repository.CategoryRepository;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name="product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private double price;

    @Column(name="photo")
    private String photo;



    @ManyToOne()
    @JoinColumn(name="category_id")
    private Category category;

    public Product(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Transient  //do not persist in a database.
    public String getPhotosImagePath() {
        if (photo == null || this.getCategory().getId() == null) return null;

        return "/product-photos/" + this.getCategory().getId() + "/" + photo;
    }
}
