package ar.challenge.shipping.controller;

import ar.challenge.shipping.model.Event;
import ar.challenge.shipping.model.Shipment;

import ar.challenge.shipping.model.Tracking;
import ar.challenge.shipping.service.ShipmentService;
import ar.challenge.shipping.service.TrackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private TrackingService trackingService;

    @RequestMapping(value="/api/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@Valid @RequestBody Shipment shipment) throws HttpMessageNotWritableException {
        shipmentService.saveShipment(shipment);
        return ResponseEntity.ok().body("Shipment registered successfully!");
    }

    @RequestMapping(value="/api/push", method = RequestMethod.PUT)
    public Event getShipmentByReference(@RequestBody Tracking traking){
        Event event = new Event();
        event.setReference(traking.getReference());
        event.setStatus(trackingService.processTracking(traking));
        log.info(event.toString());
        return event;
    }
}
