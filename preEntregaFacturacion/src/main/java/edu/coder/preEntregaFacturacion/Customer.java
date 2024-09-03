package edu.coder.preEntregaFacturacion;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;


import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LASTNAME")
    private String LastName;

    @Column(name = "DOCUMENT_TYPE")
    private String document_type;

    @Column(name = "N_DOCUMENT")
    private long n_document;

    @Column(name = "PHONE")
    private long phone;

    @Column(name = "EMAIL")
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Sale> sales;

    public Customer() {
    }

    public Customer(String name, String lastName, String document_type, long n_document, long phone, String email) {
        this.name = name;
        LastName = lastName;
        this.document_type = document_type;
        this.n_document = n_document;
        this.phone = phone;
        this.email = email;

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

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public long getN_document() {
        return n_document;
    }

    public void setN_document(long n_document) {
        this.n_document = n_document;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address= address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && n_document == customer.n_document && phone == customer.phone && Objects.equals(name, customer.name) && Objects.equals(LastName, customer.LastName) && Objects.equals(document_type, customer.document_type) && Objects.equals(email, customer.email) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, LastName, document_type, n_document, phone, email, address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", LastName='" + LastName + '\'' +
                ", document_type='" + document_type + '\'' +
                ", n_document=" + n_document +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
