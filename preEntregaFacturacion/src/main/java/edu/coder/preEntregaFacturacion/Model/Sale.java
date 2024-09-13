package edu.coder.preEntregaFacturacion.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table
public class Sale {

    //CREATE OF COLUMNS FOR TABLE SELL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SALE_DATE")
    private Date saleDate;

    @Column(name = "TOTAL_AMOUNT")
    private int totalAmount;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;


    //CREATE RELATION TO OTHERS TABLES

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<SaleProduct> saleProduct;

    public Sale() {
    }

    public Sale(Date saleDate, int totalAmount, Customer customer, Double totalPrice) {
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
        this.customer = customer;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){  this.id = id;};

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<SaleProduct> getSaleProduct() {
        return saleProduct;
    }

    public void setSaleProducts(List<SaleProduct> saleProduct) {
        this.saleProduct = saleProduct;
    }

    public double getTotalPrice() { return totalPrice;}

    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id) && Objects.equals(saleDate, sale.saleDate) && Objects.equals(totalAmount, sale.totalAmount) && Objects.equals(customer, sale.customer) && Objects.equals(saleProduct, sale.saleProduct)  && Objects.equals(totalPrice, sale.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saleDate, totalAmount, customer, saleProduct, totalPrice);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", saleDate=" + saleDate +
                ", totalAmount=" + totalAmount +
                ", totalPrice =" + totalPrice +
                ", customer=" + customer +
                '}';
    }
}
