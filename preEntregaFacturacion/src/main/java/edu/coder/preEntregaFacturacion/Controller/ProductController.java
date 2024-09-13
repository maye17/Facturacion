package edu.coder.preEntregaFacturacion.Controller;
import edu.coder.preEntregaFacturacion.Model.Product;
import edu.coder.preEntregaFacturacion.Service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Get all products




    //Get product by Id
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity <?> getProductById(@PathVariable(name = "id") Long id) {
        Optional <Product> product = productService.findProductById(id);
        if(product.isPresent()){
            return ResponseEntity.ok(product);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> savedProduct(@RequestBody Product product){
        try {
            Product savedProduct =  productService.createProduct(product);
            return ResponseEntity.created(URI.create("")).body(savedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }

    // Actualizar el stock del producto
    @PutMapping(value = "/{id}/stock", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateProductStock(@PathVariable(name = "id") Long id, @RequestParam int quantitySold) {
        try {
            Product updatedProduct = productService.updateProductStock(id, quantitySold);
            return ResponseEntity.ok(updatedProduct);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error actualizando el stock");
        }
    }


}
