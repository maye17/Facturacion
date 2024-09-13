package edu.coder.preEntregaFacturacion.Service;
import edu.coder.preEntregaFacturacion.Repository.CustomerRepository;
import edu.coder.preEntregaFacturacion.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> findCustomerById(Long idCustomer) {
        return customerRepository.findById(idCustomer);
    }
}
