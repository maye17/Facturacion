package edu.coder.preEntregaFacturacion.Repository;

import edu.coder.preEntregaFacturacion.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends JpaRepository<Address,Long> {

}
