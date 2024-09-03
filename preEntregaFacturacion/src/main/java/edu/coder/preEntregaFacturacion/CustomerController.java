package edu.coder.preEntregaFacturacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
@RestController
@RequestMapping("/Customer")
public class CustomerController {

    @Autowired
private CustomerService customerService;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity <?> getCustomerById(@PathVariable(name = "id") Long id) {
        Optional <Customer> customer = customerService.findCustomerById(id);
        if(customer.isPresent()){
            return ResponseEntity.ok(customer);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
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
