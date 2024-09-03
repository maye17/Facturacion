package edu.coder.preEntregaFacturacion;

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
        // Verificar si saleProduct es null
        if (saleProduct == null) {
            throw new IllegalArgumentException("SaleProduct cannot be null");
        }

        // Verificar que el objeto sale no sea null
        if (saleProduct.getSale() == null || saleProduct.getSale().getId() == null) {
            throw new IllegalArgumentException("Sale ID cannot be null");
        }

        // Verificar que el objeto product no sea null
        if (saleProduct.getProduct() == null || saleProduct.getProduct().getId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }

        // Recuperar la venta existente por su ID
        Optional<Sale> saleOpt = saleRepository.findById(saleProduct.getSale().getId());
        if (saleOpt.isPresent()) {
            saleProduct.setSale(saleOpt.get());
        } else {
            throw new RuntimeException("Sale not found");
        }

        // Recuperar el producto existente por su ID
        Optional<Product> productOpt = productRepository.findById(saleProduct.getProduct().getId());
        if (productOpt.isPresent()) {
            saleProduct.setProduct(productOpt.get());
        } else {
            throw new RuntimeException("Product not found");
        }

        // Guardar el SaleProduct con las entidades completas
        return saleProductRepository.save(saleProduct);
    }

    public Optional<SaleProduct> findSaleProductById(Long idSaleProduct) {
        return saleProductRepository.findById(idSaleProduct);
    }


}
