package edu.coder.preEntregaFacturacion.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table (name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID for customer", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "name to customer", example = "peter", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "NAME")
    private String name;

    @Schema(description = "lastname to customer", example = "smith", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "LASTNAME")
    private String lastName;

    @Schema(description = "document type to customer", example = "DNI", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "DOCUMENT_TYPE")
    private String document_type;

    @Schema(description = "document number to customer", example = "90987789", requiredMode = Schema.RequiredMode.REQUIRED)
    @Column(name = "N_DOCUMENT")
    private long n_document;

    @Schema(description = "Phone number to customer", example = "1130303435", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Column(name = "PHONE")
    private long phone;
    @Schema(description = "email to customer", example = "test@example.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Column(name = "EMAIL")
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Schema(description = "some address to customer", example = "Av Antartida 2250", accessMode = Schema.AccessMode.READ_ONLY)
    private List<Address> address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Schema(description = "Some sale to customer", example = "1 Aceite 5L", accessMode = Schema.AccessMode.READ_ONLY)
    private List<Sale> sales;

    public Customer() {
    }

    public Customer(String name, String lastName, String document_type, long n_document, long phone, String email) {
        this.name = name;
        this.lastName = lastName;
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
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return Objects.equals(id, customer.id) && n_document == customer.n_document && phone == customer.phone && Objects.equals(name, customer.name) && Objects.equals(lastName, customer.lastName) && Objects.equals(document_type, customer.document_type) && Objects.equals(email, customer.email) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, document_type, n_document, phone, email, address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", LastName='" + lastName + '\'' +
                ", document_type='" + document_type + '\'' +
                ", n_document=" + n_document +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
