package edu.coder.preEntregaFacturacion.Service;

import edu.coder.preEntregaFacturacion.Model.Product;
import edu.coder.preEntregaFacturacion.Model.Sale;
import edu.coder.preEntregaFacturacion.Model.SaleProduct;
import edu.coder.preEntregaFacturacion.Repository.ProductRepository;
import edu.coder.preEntregaFacturacion.Repository.SaleProductRepository;
import edu.coder.preEntregaFacturacion.Repository.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SaleProductService {
    @Autowired
    private SaleProductRepository saleProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleService saleService; // Para actualizar el totalAmount y totalPrice

    public SaleProduct createSaleProduct(SaleProduct saleProduct) {
        if (saleProduct.getSale() == null || saleProduct.getSale().getId() == null) {
            throw new IllegalArgumentException("Sale ID cannot be null");
        }

        // Verificar que el objeto product no sea null
        if (saleProduct.getProduct() == null || saleProduct.getProduct().getId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }


        try {

            //Recupera el producto existente
            Product product = productRepository.findById(saleProduct.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            //Recupera la venta existente
            Sale sale = saleRepository.findById(saleProduct.getSale().getId())
                    .orElseThrow(() -> new RuntimeException("Sale not found"));


            int quantityToSell = saleProduct.getQuantity(); // Suponiendo que esta propiedad define la cantidad vendida.
            int currentStock = product.getStock(); // Suponiendo que esta propiedad define el stock actual del producto.
            if (currentStock < quantityToSell) {
                throw new RuntimeException("Producto no disponible: Stock insuficiente.");
            }


            // Resta la cantidad del stock
            product.setStock(currentStock - quantityToSell);


            // Save the change to product
            productRepository.save(product);

            // Assign the entities recovered
            saleProduct.setSale(sale);
            saleProduct.setProduct(product);

            // Calculate the subtotal of that sale by product
            double subtotal = product.getPrice() * quantityToSell;
            saleProduct.setSubtotal(subtotal);

            // Guarda el SaleProduct
            SaleProduct savedSaleProduct = saleProductRepository.save(saleProduct);

            // Actualiza los totales de la venta
            saleService.updateSaleTotals(sale);

            return savedSaleProduct;

        } catch (Exception e) {
            // Manejo general de excepciones
            throw new RuntimeException("An unexpected error occurred while creating SaleProduct", e);
        }
    }
    public Optional<SaleProduct> findSaleProductById(Long idSaleProduct) {
        return saleProductRepository.findById(idSaleProduct);
    }
}
