package com.amazon.ata.service;

import com.amazon.ata.cost.MonetaryCostStrategy;
import com.amazon.ata.dao.PackagingDAO;
import com.amazon.ata.datastore.PackagingDatastore;
import com.amazon.ata.exceptions.UnknownFulfillmentCenterException;
import com.amazon.ata.types.FulfillmentCenter;
import com.amazon.ata.types.Item;
import com.amazon.ata.types.Packaging;
import com.amazon.ata.types.ShipmentOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.mockito.InjectMocks;
import org.mockito.Mock;

class ShipmentServiceTest {
    private Item smallItem = Item.builder()
            .withHeight(BigDecimal.valueOf(1))
            .withWidth(BigDecimal.valueOf(1))
            .withLength(BigDecimal.valueOf(1))
            .withAsin("abcde")
            .build();

    private Item largeItem = Item.builder()
            .withHeight(BigDecimal.valueOf(1000))
            .withWidth(BigDecimal.valueOf(1000))
            .withLength(BigDecimal.valueOf(1000))
            .withAsin("12345")
            .build();

    private FulfillmentCenter existentFC = new FulfillmentCenter("ABE2");
    private FulfillmentCenter nonExistentFC = new FulfillmentCenter("NonExistentFC");

    // private ShipmentService shipmentService = new ShipmentService(new PackagingDAO(new PackagingDatastore()),
    //        new MonetaryCostStrategy());

    @Mock
    private PackagingDAO packagingDAO;

    @InjectMocks
    private ShipmentService shipmentService;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void findBestShipmentOption_existentFCAndItemCanFit_returnsShipmentOption() throws Exception {
        // GIVEN
        List<ShipmentOption> shipmentOptionList = new ArrayList<>();

        shipmentOptionList.add(ShipmentOption.builder()
                .withItem(smallItem)
                .withFulfillmentCenter(existentFC)
                .build());

        when(packagingDAO.findShipmentOptions(smallItem, existentFC)).thenReturn(shipmentOptionList);

        // WHEN
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(smallItem, existentFC);

        // THEN
        assertNull(shipmentOption);
    }

    @Test
    void findBestShipmentOption_existentFCAndItemCannotFit_returnsShipmentOption() throws Exception {
        // GIVEN
        List<ShipmentOption> shipmentOptionList = new ArrayList<>();

        shipmentOptionList.add(ShipmentOption.builder()
                .withItem(largeItem)
                .withFulfillmentCenter(existentFC)
                .build());

        when(packagingDAO.findShipmentOptions(largeItem, existentFC)).thenReturn(shipmentOptionList);

        // WHEN
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(largeItem, existentFC);

        // THEN
        assertNull(shipmentOption);
    }

    @Test
    void findBestShipmentOption_nonExistentFCAndItemCanFit_returnsShipmentOption() throws Exception {
        // GIVEN
        List<ShipmentOption> shipmentOptionList = new ArrayList<>();

        shipmentOptionList.add(ShipmentOption.builder()
                .withItem(smallItem)
                .withFulfillmentCenter(nonExistentFC)
                .build());

        when(packagingDAO.findShipmentOptions(smallItem, nonExistentFC)).thenReturn(shipmentOptionList);

        // WHEN
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(smallItem, nonExistentFC);

        // THEN
        assertNull(shipmentOption);

    }

    @Test
    void findBestShipmentOption_nonExistentFCAndItemCannotFit_returnsShipmentOption() throws Exception {
        // GIVEN
        List<ShipmentOption> shipmentOptionList = new ArrayList<>();

        shipmentOptionList.add(ShipmentOption.builder()
                .withItem(largeItem)
                .withFulfillmentCenter(nonExistentFC)
                .build());

        when(packagingDAO.findShipmentOptions(largeItem, nonExistentFC)).thenReturn(shipmentOptionList);

        // WHEN
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(largeItem, nonExistentFC);

        // THEN
        assertNull(shipmentOption);
    }

    // My test
    /*
    @Test
    public void testFindShipmentOptionThrowsRuntimeExceptionForUnknownFulfillmentCenter() {
        // GIVEN
        PackagingDAO packagingDAO = new PackagingDAO();
        CostStrategy costStrategy = new CostStrategy(); // replace with your actual cost strategy class
        ShipmentService shipmentService = new ShipmentService(packagingDAO, costStrategy);
        Item item = new Item(); // replace with actual item
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(); // replace with unknown fulfillment center

        try {
            // WHEN
            shipmentService.findShipmentOption(item, fulfillmentCenter);
            fail("Expected an RuntimeException to be thrown");
        } catch (RuntimeException e) {
            // THEN
            assertThat(e.getMessage(), is("Unknown Fulfillment Center"));
        }
    }
    */
}