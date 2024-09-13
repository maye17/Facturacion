package edu.coder.preEntregaFacturacion.Service;

import edu.coder.preEntregaFacturacion.Model.Address;
import edu.coder.preEntregaFacturacion.Model.Customer;
import edu.coder.preEntregaFacturacion.Repository.AddressRepository;
import edu.coder.preEntregaFacturacion.Repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Address> saveAddressesForCustomer(Long customerId, List<Address> addresses) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            addresses.forEach(address -> address.setCustomer(customer)); // Asigna el cliente a cada dirección
            return addressRepository.saveAll(addresses); // Guarda todas las direcciones
        } else {
            throw new EntityNotFoundException("Customer not found");
        }
    }


    public Optional<Address> findAddressById(Long id) {
        return addressRepository.findById(id); // Utiliza el método findById de Spring Data JPA
    }
}