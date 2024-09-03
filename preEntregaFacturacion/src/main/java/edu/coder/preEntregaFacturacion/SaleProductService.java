package edu.coder.preEntregaFacturacion;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SaleProductService {
    @Autowired
    private SaleProductRepository saleProductRepository;

    @Autowired
    private SaleRepository saleRepository;


    @Autowired
    private ProductRepository productRepository;

    public SaleProduct createSaleProduct(SaleProduct saleProduct) {
        if (saleProduct.getSale() == null || saleProduct.getSale().getId() == null) {
            throw new IllegalArgumentException("Sale ID cannot be null");
        }

        // Verificar que el objeto product no sea null
        if (saleProduct.getProduct() == null || saleProduct.getProduct().getId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        try {
            // Recuperar la venta existente por su ID
            Sale sale = saleRepository.findById(saleProduct.getSale().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Sale not found"));

            // Recuperar el producto existente por su ID
            Product product = productRepository.findById(saleProduct.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            // Asignar las entidades recuperadas
            saleProduct.setSale(sale);
            saleProduct.setProduct(product);

            // Guardar el SaleProduct con las entidades completas
            return saleProductRepository.save(saleProduct);

        } catch (Exception e) {
            // Manejo general de excepciones
            throw new RuntimeException("An unexpected error occurred while creating SaleProduct", e);
        }

    }


    public Optional<SaleProduct> findSaleProductById(Long idSaleProduct) {
        return saleProductRepository.findById(idSaleProduct);
    }


}
