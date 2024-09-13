package edu.coder.preEntregaFacturacion.Controller;
import edu.coder.preEntregaFacturacion.Model.Address;
import edu.coder.preEntregaFacturacion.Service.AddressService;
import edu.coder.preEntregaFacturacion.Service.CustomerService;
import edu.coder.preEntregaFacturacion.Model.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/Customer")
@Tag(name="Customer", description = "Operaciones relacionadas con los clientes")
public class CustomerController {

    @Autowired
private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
  @Operation(summary = "Find to customer for Id", description = "Find to customer for id in the system")
    public ResponseEntity <?> getCustomerById(@PathVariable(name = "id") Long id) {
        Optional <Customer> customer = customerService.findCustomerById(id);
        if(customer.isPresent()){
            return ResponseEntity.ok(customer);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Add a new customer", description = "Add a new customer in the system")
    public ResponseEntity<?> savedCustomer(@RequestBody Customer customer){
        try {
            Customer savedCustomer =  customerService.createCustomer(customer);
            return ResponseEntity.created(URI.create("")).body(savedCustomer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping(value = "/{customerId}/addresses", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add multiple addresses to a customer", description = "Add multiple addresses to a customer by customer ID")
    public ResponseEntity<?> addAddressesToCustomer(@PathVariable Long customerId, @RequestBody List<Address> addresses) {
        try {
            List<Address> savedAddresses = addressService.saveAddressesForCustomer(customerId, addresses);
            return ResponseEntity.created(URI.create("/Customer/" + customerId + "/addresses")).body(savedAddresses);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occurred: " + e.getMessage());
        }
    }
}
