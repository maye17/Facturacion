package edu.coder.preEntregaFacturacion.Repository;
import edu.coder.preEntregaFacturacion.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

