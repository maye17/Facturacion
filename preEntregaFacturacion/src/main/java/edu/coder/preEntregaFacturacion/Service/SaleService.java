package edu.coder.preEntregaFacturacion.Service;
import edu.coder.preEntregaFacturacion.Repository.CustomerRepository;
import edu.coder.preEntregaFacturacion.Model.Customer;
import edu.coder.preEntregaFacturacion.Model.Sale;
import edu.coder.preEntregaFacturacion.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Sale createSale(Sale sale) {
        // Verificar si el cliente existe en la base de datos
        Optional<Customer> customerOpt = customerRepository.findById(sale.getCustomer().getId());

        if (customerOpt.isPresent()) {

            //Assign to the customer existing to the sale
            sale.setCustomer(customerOpt.get());
        } else {
            throw new RuntimeException("Customer not found");
        }

        // Saved the sale
        return saleRepository.save(sale);
    }
    public Optional<Sale> findSaleById(Long idSale) {
        return saleRepository.findById(idSale);
    }
}
