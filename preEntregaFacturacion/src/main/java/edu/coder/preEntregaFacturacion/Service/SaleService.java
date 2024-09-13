package edu.coder.preEntregaFacturacion.Service;

import edu.coder.preEntregaFacturacion.Model.SaleProduct;
import edu.coder.preEntregaFacturacion.Repository.CustomerRepository;
import edu.coder.preEntregaFacturacion.Model.Customer;
import edu.coder.preEntregaFacturacion.Model.Sale;
import edu.coder.preEntregaFacturacion.Repository.SaleProductRepository;
import edu.coder.preEntregaFacturacion.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SaleProductRepository saleProductRepository;

    public Sale createSale(Sale sale) {
        // Verificar si el cliente existe en la base de datos
        Optional<Customer> customerOpt = customerRepository.findById(sale.getCustomer().getId());

        if (customerOpt.isPresent()) {
            // Asignar el cliente existente a la venta
            sale.setCustomer(customerOpt.get());
        } else {
            throw new RuntimeException("Customer not found");
        }

        // Guardar la venta inicial sin el totalAmount ni el totalPrice
        Sale savedSale = saleRepository.save(sale);

        // Calcular el totalAmount y el totalPrice
        int totalAmount = calculateTotalAmount(savedSale.getId());
        double totalPrice = calculateTotalPrice(savedSale.getId());

        // Asignar los valores calculados a la venta
        savedSale.setTotalAmount(totalAmount);
        savedSale.setTotalPrice(totalPrice);

        // Guardar la venta con el totalAmount y totalPrice actualizados
        return saleRepository.save(savedSale);
    }

    public void updateSaleTotals(Sale sale) {
        int totalAmount = calculateTotalAmount(sale.getId());
        double totalPrice = calculateTotalPrice(sale.getId());

        sale.setTotalAmount(totalAmount);
        sale.setTotalPrice(totalPrice);

        saleRepository.save(sale);
    }


    public Optional<Sale> findSaleById(Long idSale) {
        return saleRepository.findById(idSale);
    }

    // Método para calcular el totalAmount
    private int calculateTotalAmount(Long saleId) {
        // Obtener todos los SaleProduct asociados a esta venta
        List<SaleProduct> saleProducts = saleProductRepository.findBySaleId(saleId);

        // Sumar las cantidades de cada producto vendido
        return saleProducts.stream()
                .mapToInt(SaleProduct::getQuantity)
                .sum();
    }

    // Método para calcular el totalPrice
    private double calculateTotalPrice(Long saleId) {
        // Obtener todos los SaleProduct asociados a esta venta
        List<SaleProduct> saleProducts = saleProductRepository.findBySaleId(saleId);

        // Sumar los subtotales de cada SaleProduct
        return saleProducts.stream()
                .mapToDouble(saleProduct -> saleProduct.getProduct().getPrice() * saleProduct.getQuantity())
                .sum();
    }
}
