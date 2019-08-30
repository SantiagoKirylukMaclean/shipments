package ar.challenge.shipping.repository;

import ar.challenge.shipping.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Shipment findByReference(String reference);

}
