package edu.coder.preEntregaFacturacion;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    private Double totalAmount;


    //CREATE RELATION TO OTHERS TABLES

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SaleProduct> saleProduct;

    public Sale() {
    }

    public Sale(Date saleDate, Double totalAmount, Customer customer) {
        this.saleDate = saleDate;
        this.totalAmount = totalAmount;
        this.customer = customer;
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

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return id == sale.id && Objects.equals(saleDate, sale.saleDate) && Objects.equals(totalAmount, sale.totalAmount) && Objects.equals(customer, sale.customer) && Objects.equals(saleProduct, sale.saleProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, saleDate, totalAmount, customer, saleProduct);
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", saleDate=" + saleDate +
                ", totalAmount=" + totalAmount +
                ", customer=" + customer +
                '}';
    }
}
