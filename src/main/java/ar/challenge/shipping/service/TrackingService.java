package ar.challenge.shipping.service;

import ar.challenge.shipping.model.Tracking;
import org.springframework.stereotype.Service;

@Service
public interface TrackingService {

    String processTracking(Tracking tracking);
}
