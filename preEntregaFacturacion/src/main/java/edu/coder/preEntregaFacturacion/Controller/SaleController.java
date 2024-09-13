package edu.coder.preEntregaFacturacion.Controller;
import edu.coder.preEntregaFacturacion.Model.Sale;
import edu.coder.preEntregaFacturacion.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;


    //Get product by Id
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity <?> getSaleById(@PathVariable(name = "id") Long id) {
        Optional<Sale> sale = saleService.findSaleById(id);
        if(sale.isPresent()){
            return ResponseEntity.ok(sale);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> savedSale(@RequestBody Sale sale){
        try {
            Sale savedSale =  saleService.createSale(sale);
            return ResponseEntity.created(URI.create("")).body(savedSale);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
