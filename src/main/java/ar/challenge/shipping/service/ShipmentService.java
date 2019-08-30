package ar.challenge.shipping.service;

import ar.challenge.shipping.model.Shipment;
import org.springframework.stereotype.Service;


@Service
public interface ShipmentService {

    void saveShipment(Shipment shipment);

    Shipment getShipmentsByReference(String reference);
}
