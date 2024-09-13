package edu.coder.preEntregaFacturacion.Service;

import edu.coder.preEntregaFacturacion.Model.Product;
import edu.coder.preEntregaFacturacion.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findProductById(Long idProduct) {
        return productRepository.findById(idProduct);
    }

    public Product updateProductStock(Long productId, int quantitySold) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        int currentStock = product.getStock();

        if (currentStock < quantitySold) {
            throw new RuntimeException("Producto no disponible: Stock insuficiente.");
        }

        // Restar la cantidad vendida del stock actual
        product.setStock(currentStock - quantitySold);

        return productRepository.save(product);
    }
}
