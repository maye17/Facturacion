package edu.coder.preEntregaFacturacion.Controller;
import edu.coder.preEntregaFacturacion.Service.CustomerService;
import edu.coder.preEntregaFacturacion.Model.Customer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
@RestController
@RequestMapping("/Customer")
@Tag(name="Customer", description = "Operaciones relacionadas con los clientes")
public class CustomerController {

    @Autowired
private CustomerService customerService;

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

}
