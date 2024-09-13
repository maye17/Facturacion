package edu.coder.preEntregaFacturacion.Repository;
import edu.coder.preEntregaFacturacion.Model.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SaleProductRepository extends JpaRepository<SaleProduct,Long>{
    List<SaleProduct> findBySaleId(Long saleId);
}
