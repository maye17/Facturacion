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


    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }
    public List<Address> saveAddressesForCustomer(Long customerId, List<Address> addresses) {
        // Verificar si el cliente existe
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        System.out.println("El cliente no existe");

        // Asignar el cliente a cada dirección y guardarlas
        addresses.forEach(address -> {
            address.setCustomer(customer);
            addressRepository.save(address);
        });

        return addresses;
    }


    public Optional<Address> findAddressById(Long addressId) {
        return addressRepository.findById(addressId); // Utiliza el método findById de Spring Data JPA
    }
}