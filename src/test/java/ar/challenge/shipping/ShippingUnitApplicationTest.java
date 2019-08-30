package ar.challenge.shipping;

import ar.challenge.shipping.model.Parcels;
import ar.challenge.shipping.model.Shipment;
import ar.challenge.shipping.model.Status;
import ar.challenge.shipping.model.Tracking;
import ar.challenge.shipping.repository.ShipmentRepository;
import ar.challenge.shipping.service.ShipmentService;
import ar.challenge.shipping.service.TrackingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShippingUnitApplicationTest {

    @Autowired
    private TrackingService trackingService;

    @MockBean
    private ShipmentRepository shipmentRepository;

    @Before
    public void setUp() {
        Shipment shipment = new Shipment();
        Set<Parcels> parcels = new HashSet<>();
        Parcels parcel1 = new Parcels();
        Parcels parcel2 = new Parcels();

        parcel1.setWeight(1);
        parcel1.setWidth(10);
        parcel1.setHeight(10);
        parcel1.setLenght(10);

        parcel2.setWeight(2);
        parcel2.setWidth(20);
        parcel2.setHeight(20);
        parcel2.setLenght(20);

        parcels.add(parcel1);
        parcels.add(parcel2);

        shipment.setReference("ABCD123456");
        shipment.setParcels(parcels);

        Mockito.when(shipmentRepository.findByReference("ABCD123456")).thenReturn(shipment);

    }

    @Test
    public void TrackingNullWeightTest() {
        Tracking tracking = new Tracking();
        tracking.setReference("ABCD123456");
        tracking.setParcels(2);
        tracking.setWeight(2);
        tracking.setStatus("WAITING_IN_HUB");

        assertEquals(Status.INCOMPLETE.toString(),trackingService.processTracking(tracking));
    }

    @Test
    public void TrackingNotDeliveredTest() {
        Tracking tracking = new Tracking();
        tracking.setReference("ABCD123456");
        tracking.setParcels(2);
        tracking.setWeight(null);
        tracking.setStatus("WAITING_IN_HUB");

        assertEquals(Status.INCOMPLETE.toString(),trackingService.processTracking(tracking));
    }

    @Test
    public void TrackingWeightGratherOrEqualTest() {
        Tracking tracking = new Tracking();
        tracking.setReference("ABCD123456");
        tracking.setParcels(2);
        tracking.setWeight(30);
        tracking.setStatus("DELIVERED");

        assertEquals(Status.NOT_NEEDED.toString(),trackingService.processTracking(tracking));
    }

    @Test
    public void TrackingNotFoundReferenceTest() {
        Tracking tracking = new Tracking();
        tracking.setReference("EFGH123456");
        tracking.setParcels(2);
        tracking.setWeight(30);
        tracking.setStatus("DELIVERED");

        assertEquals(Status.NOT_FOUND.toString(),trackingService.processTracking(tracking));
    }

    @Test
    public void TrackingConciliationTest() {
        Tracking tracking = new Tracking();
        tracking.setReference("ABCD123456");
        tracking.setParcels(2);
        tracking.setWeight(2);
        tracking.setStatus("DELIVERED");

        assertEquals(Status.CONCILLIATION_REQUEST.toString(),trackingService.processTracking(tracking));
    }




}
