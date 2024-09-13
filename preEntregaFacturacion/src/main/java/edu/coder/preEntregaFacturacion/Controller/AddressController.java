package edu.coder.preEntregaFacturacion.Controller;
import edu.coder.preEntregaFacturacion.Model.Address;
import edu.coder.preEntregaFacturacion.Model.Customer;
import edu.coder.preEntregaFacturacion.Service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/address")
@Tag(name="Address for customer", description = "add address for customer")

public class AddressController {
    @Autowired

    private AddressService addressService;
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Find to address for id", description = "Find to address for id in the system")
    public ResponseEntity <?> getAddressById(@PathVariable(name = "id") Long id) {
        Optional <Address> address = addressService.findAddressById(id);
        if(address.isPresent()){
            return ResponseEntity.ok(address);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/Customer/{customerId}")
    public ResponseEntity<?> addAddressesToCustomer(@PathVariable Long customerId, @RequestBody List<Address> addresses) {
        try {
            List<Address> savedAddresses = addressService.saveAddressesForCustomer(customerId, addresses);
            return ResponseEntity.created(URI.create("/address/customer/" + customerId)).body(savedAddresses);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occurred: " + e.getMessage());
        }
    }

}
