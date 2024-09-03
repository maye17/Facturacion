package edu.coder.preEntregaFacturacion;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saleproducts")
public class SaleProductController {

    @Autowired
    private SaleProductService saleProductService;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getSaleProductById(@PathVariable(name = "id") Long id) {
        Optional<SaleProduct> saleProduct = saleProductService.findSaleProductById(id);
        if(saleProduct.isPresent()){
            return ResponseEntity.ok(saleProduct);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> savedSaleProduct(@RequestBody SaleProduct saleProduct){
        try {
            SaleProduct savedSaleProduct =  saleProductService.createSaleProduct(saleProduct);
            return ResponseEntity.created(URI.create("")).body(savedSaleProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
