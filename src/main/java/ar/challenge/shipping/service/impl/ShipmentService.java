package ar.challenge.shipping.service.impl;

import ar.challenge.shipping.model.Shipment;
import ar.challenge.shipping.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShipmentService implements ar.challenge.shipping.service.ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    /**
     * Save shipment
     *
     * @param shipment Shipment object
     * @return
     */
    public void saveShipment(Shipment shipment) {
        shipmentRepository.save(shipment);
    }

    /**
     * Return shipment by reference number
     *
     * @param reference Is a String whit reference numbre
     * @return Shipment
     */
   public Shipment getShipmentsByReference(String reference){
        return shipmentRepository.findByReference(reference);
   }


}
