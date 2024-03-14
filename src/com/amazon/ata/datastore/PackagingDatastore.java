package com.amazon.ata.datastore;

import com.amazon.ata.types.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class PackagingDatastore {

    /**
     * The stored pairs of fulfillment centers to the packaging options they support.
     */
    private final List<FcPackagingOption> fcPackagingOptions = Arrays.asList(
            createBoxPackagingOption("IND1", "10", "10", "10"),
            createBoxPackagingOption("ABE2", "20", "20", "20"),
            createBoxPackagingOption("ABE2", "40", "40", "40"),
            createBoxPackagingOption("YOW4", "10", "10", "10"),
            createBoxPackagingOption("YOW4", "20", "20", "20"),
            createBoxPackagingOption("YOW4", "60", "60", "60"),
            createBoxPackagingOption("IAD2", "20", "20", "20"),
            createBoxPackagingOption("IAD2", "20", "20", "20"),
            createBoxPackagingOption("PDX1", "40", "40", "40"),
            createBoxPackagingOption("PDX1", "60", "60", "60"),
            createBoxPackagingOption("PDX1", "60", "60", "60"),

            //polyBags
            createPolyBagFcPackagingOption("IAD2", "2000"),
            createPolyBagFcPackagingOption("IAD2", "10000"),
            createPolyBagFcPackagingOption("IAD2", "5000"),
            createPolyBagFcPackagingOption("YOW4", "2000"),
            createPolyBagFcPackagingOption("YOW4", "5000"),
            createPolyBagFcPackagingOption("YOW4", "10000"),
            createPolyBagFcPackagingOption("IND1", "2000"),
            createPolyBagFcPackagingOption("IND1", "5000"),
            createPolyBagFcPackagingOption("ABE2", "2000"),
            createPolyBagFcPackagingOption("ABE2", "6000"),
            createPolyBagFcPackagingOption("PDX1", "5000"),
            createPolyBagFcPackagingOption("PDX1", "10000"),
            createPolyBagFcPackagingOption("YOW4", "5000")

    );

    /**
     * Create fulfillment center packaging option from provided parameters.
     */
    private FcPackagingOption createBoxPackagingOption(String fcCode,
                                                       String length, String width, String height) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Packaging packaging = new Box(Material.CORRUGATE, new BigDecimal(length), new BigDecimal(width), new BigDecimal(height));
        return new FcPackagingOption(fulfillmentCenter, packaging);
    }

    private FcPackagingOption createPolyBagFcPackagingOption(String fcCode, String polyBagSize) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Packaging packaging = new PolyBag(Material.LAMINATED_PLASTIC, new BigDecimal(polyBagSize));

        return new FcPackagingOption(fulfillmentCenter, packaging);
    }

    public List<FcPackagingOption> getFcPackagingOptions() {
        return fcPackagingOptions;
    }
}
