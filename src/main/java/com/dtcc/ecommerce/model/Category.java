package com.dtcc.ecommerce.model;
//Category Entity class.
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "category")
 //   @OneToMany(cascade = CascadeType.ALL) //onetomany unidirectional
   // @JoinColumn(name="category_id")
    private List<Product> products;

    public Category(){}

    public Category(String name) {
        this.name = name;
    }

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    //convenience methods for bi-directional relationship
    public void addProduct(Product tempProduct){
        if(products==null){
            products=new ArrayList<>();
        }
        products.add(tempProduct);
        tempProduct.setCategory(this);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
