package edu.coder.preEntregaFacturacion;

import jakarta.persistence.*;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table
public class SaleProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PRICE")
    private Double price;

//   @ManyToOne
//   @JoinColumn(name = "sale_id", nullable = false)
//   private Sale sale;

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
   private Sale sale;
//   @ManyToOne
//   @JoinColumn(name = "product_id",nullable = false)
//    private Product product;

   @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
    private Product product;

    public SaleProduct() {
    }

    public SaleProduct(Integer quantity, Double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleProduct that = (SaleProduct) o;
        return Objects.equals(id, that.id) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) && Objects.equals(sale, that.sale) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, price, sale, product);
    }

    @Override
    public String toString() {
        return "SaleProduct{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }


}

