package edu.coder.preEntregaFacturacion.Model;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(name="STREET")
    private String street;

    @Column(name = "STREET_NUMBER")
    private long street_number;

    @Column(name = "FLAT_NUMBER")
    private String flat_number;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "POSTAL_CODE")
    private String PC;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Address() {
    }

    public Address(int id, String street, long street_number, String flat_number, String city, String state, String PC) {
        Id = id;
        this.street = street;
        this.street_number = street_number;
        this.flat_number = flat_number;
        this.city = city;
        this.state = state;
        this.PC = PC;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getStreet_number() {
        return street_number;
    }

    public void setStreet_number(long street_number) {
        this.street_number = street_number;
    }

    public String getFlat_number() {
        return flat_number;
    }

    public void setFlat_number(String flat_number) {
        this.flat_number = flat_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPC() {
        return PC;
    }

    public void setPC(String PC) {
        this.PC = PC;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Id == address.Id && street_number == address.street_number && Objects.equals(street, address.street) && Objects.equals(flat_number, address.flat_number) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(PC, address.PC) && Objects.equals(customer, address.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, street, street_number, flat_number, city, state, PC, customer);
    }

    @Override
    public String toString() {
        return "Address{" +
                "Id=" + Id +
                ", street='" + street + '\'' +
                ", street_number=" + street_number +
                ", flat_number='" + flat_number + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", PC='" + PC + '\'' +
                '}';
    }
}
