package edu.coder.preEntregaFacturacion.Model;

import jakarta.persistence.*;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="PRODUCT_NAME")
    private String product_name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private float price;

    @Column(name = "STOCK")
    private int stock;

    public Product() {
    }

    public Product(String product_name, String description, float price, int stock) {
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.stock= stock;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(price, product.price) == 0 && Objects.equals(id, product.id) && Objects.equals(product_name, product.product_name) && Objects.equals(description, product.description) &&  Objects.equals(stock, product.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product_name, description, price,stock);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product_name='" + product_name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ",stock =" + stock +
                '}';
    }
}
