package edu.coder.preEntregaFacturacion.Repository;
import edu.coder.preEntregaFacturacion.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 @Repository

 public interface ProductRepository extends JpaRepository<Product,Long>{
}
