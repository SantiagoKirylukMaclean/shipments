package ar.challenge.shipping.service.impl;

import ar.challenge.shipping.model.Parcels;
import ar.challenge.shipping.model.Shipment;
import ar.challenge.shipping.model.Status;
import ar.challenge.shipping.model.Tracking;
import ar.challenge.shipping.repository.TrackingRepository;
import ar.challenge.shipping.service.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TrackingService implements ar.challenge.shipping.service.TrackingService {

    @Autowired
    ShipmentService shipmentService;

    @Autowired
    TrackingRepository trackingRepository;

    /**
     * Process tracking
     *
     * @param tracking Tracking object to process
     * @return String process status
     */
    public String processTracking(Tracking tracking){
        Shipment shipment = shipmentService.getShipmentsByReference(tracking.getReference());
        return processStatus(shipment,tracking);
    }

    /**
     * Process status, save Tracking.
     *
     * @param tracking Tracking object to process
     * @param shipment Shipment object to process
     * @return String process status
     */
    private String processStatus(Shipment shipment, Tracking tracking) {

        trackingRepository.save(tracking);

        if (shipment == null){
            log.info("shipment is null");
            return Status.NOT_FOUND.toString();
        }else if(checkNullTrackingFields(tracking) || !tracking.getStatus().equals("DELIVERED")){
            log.info("tracking Status("+tracking.getStatus()+")");
            return Status.INCOMPLETE.toString();
        }else{
           log.info("totalShipmentParcels("+shipment.getParcels().size()+"), Tracking parcels ("+tracking.getParcels()+") " +
                    ", totalShipmentWeight("+totalWeight(shipment)+"), tracking Weight("+tracking.getWeight()+")");
           if (shipment.getParcels().size() == tracking.getParcels() && totalWeight(shipment) < tracking.getWeight()){
               return Status.NOT_NEEDED.toString();
           }else{
               return Status.CONCILLIATION_REQUEST.toString();
           }

        }
    }

    /**
     * Function to check null fields in tracking.
     *
     * @param tracking Tracking object to process
     * @return boolean with result
     */
    private boolean checkNullTrackingFields(Tracking tracking){
        if (tracking.getParcels() == null || tracking.getStatus() == null || tracking.getWeight() == null){
            log.info("Parcels("+tracking.getParcels()+"), status("+tracking.getStatus()+") or Weight("+tracking.getWeight()+")");
            return true;
        }else{
            return false;
        }
    }

    /**
     * Function to get total weight of shipment
     *
     * @param shipment Shipment object to process
     * @return int total weight of shipment
     */
    private int totalWeight(Shipment shipment){
        return shipment.getParcels().stream().filter(o -> o.getWeight() > 0 ).mapToInt(Parcels::getWeight).sum();
    }
}
