package com.amazon.ata.datastore;

import com.amazon.ata.types.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * Stores all configured packaging pairs for all fulfillment centers.
 */
public class PackagingDatastore {

    /**
     * The stored pairs of fulfillment centers to the packaging options they support.
     */
    // private final List<FcPackagingOption> fcPackagingOptions = Arrays.asList(
    private final Map<FulfillmentCenter, HashSet<FcPackagingOption>> fcPackagingOptions = new HashMap<>();
    {
        for (FcPackagingOption option : Arrays.asList(
                createFcPackagingOption("IND1", Material.CORRUGATE, "10", "10", "10"),
                createFcPackagingOption("ABE2", Material.CORRUGATE, "20", "20", "20"),
                createFcPackagingOption("ABE2", Material.CORRUGATE, "40", "40", "40"),
                createFcPackagingOption("YOW4", Material.CORRUGATE, "10", "10", "10"),
                createFcPackagingOption("YOW4", Material.CORRUGATE, "20", "20", "20"),
                createFcPackagingOption("YOW4", Material.CORRUGATE, "60", "60", "60"),
                createFcPackagingOption("IAD2", Material.CORRUGATE, "20", "20", "20"),
                createFcPackagingOption("IAD2", Material.CORRUGATE, "20", "20", "20"),
                createFcPackagingOption("PDX1", Material.CORRUGATE, "40", "40", "40"),
                createFcPackagingOption("PDX1", Material.CORRUGATE, "60", "60", "60"),
                createFcPackagingOption("PDX1", Material.CORRUGATE, "60", "60", "60"),
                createFcPackagingOption("IAD2", Material.LAMINATED_PLASTIC,"2000"),
                createFcPackagingOption("IAD2", Material.LAMINATED_PLASTIC,"10000"),
                createFcPackagingOption("IAD2", Material.LAMINATED_PLASTIC,"5000"),
                createFcPackagingOption("YOW4", Material.LAMINATED_PLASTIC,"2000"),
                createFcPackagingOption("YOW4", Material.LAMINATED_PLASTIC,"5000"),
                createFcPackagingOption("YOW4", Material.LAMINATED_PLASTIC,"10000"),
                createFcPackagingOption("IND1", Material.LAMINATED_PLASTIC,"2000"),
                createFcPackagingOption("IND1", Material.LAMINATED_PLASTIC,"5000"),
                createFcPackagingOption("ABE2", Material.LAMINATED_PLASTIC,"2000"),
                createFcPackagingOption("ABE2", Material.LAMINATED_PLASTIC,"6000"),
                createFcPackagingOption("PDX1", Material.LAMINATED_PLASTIC,"5000"),
                createFcPackagingOption("PDX1", Material.LAMINATED_PLASTIC,"10000"),
                createFcPackagingOption("YOW4", Material.LAMINATED_PLASTIC,"5000")

        )) {
            FulfillmentCenter fc = option.getFulfillmentCenter();
            if (!fcPackagingOptions.containsKey(fc)) {
                fcPackagingOptions.put(fc, new HashSet<>());
            }
            fcPackagingOptions.get(fc).add(option);
        }
    }

    /*
    createFcPackagingOption("IND1", Material.CORRUGATE, "10", "10", "10"),
    createFcPackagingOption("ABE2", Material.CORRUGATE, "20", "20", "20"),
    createFcPackagingOption("ABE2", Material.CORRUGATE, "40", "40", "40"),
    createFcPackagingOption("YOW4", Material.CORRUGATE, "10", "10", "10"),
    createFcPackagingOption("YOW4", Material.CORRUGATE, "20", "20", "20"),
    createFcPackagingOption("YOW4", Material.CORRUGATE, "60", "60", "60"),
    createFcPackagingOption("IAD2", Material.CORRUGATE, "20", "20", "20"),
    createFcPackagingOption("IAD2", Material.CORRUGATE, "20", "20", "20"),
    createFcPackagingOption("PDX1", Material.CORRUGATE, "40", "40", "40"),
    createFcPackagingOption("PDX1", Material.CORRUGATE, "60", "60", "60"),
    createFcPackagingOption("PDX1", Material.CORRUGATE, "60", "60", "60"),
    // Added for Sprint 10, Mastery Task 4, Milestone 3
    createFcPackagingOption("IAD2", Material.LAMINATED_PLASTIC, "2000"),
    createFcPackagingOption("IAD2", Material.LAMINATED_PLASTIC, "10000"),
    // Add for Sprint 10, Mastery Task 5, Milestone 3
    createFcPackagingOption("IAD2", Material.LAMINATED_PLASTIC, "5000"),
    createFcPackagingOption("YOW4", Material.LAMINATED_PLASTIC, "2000"),
    createFcPackagingOption("YOW4", Material.LAMINATED_PLASTIC, "5000"),
    createFcPackagingOption("YOW4", Material.LAMINATED_PLASTIC, "10000"),
    createFcPackagingOption("IND1", Material.LAMINATED_PLASTIC, "2000"),
    createFcPackagingOption("IND1", Material.LAMINATED_PLASTIC, "5000"),
    createFcPackagingOption("ABE2", Material.LAMINATED_PLASTIC, "2000"),
    createFcPackagingOption("ABE2", Material.LAMINATED_PLASTIC, "6000"),
    createFcPackagingOption("PDX1", Material.LAMINATED_PLASTIC, "5000"),
    createFcPackagingOption("PDX1", Material.LAMINATED_PLASTIC, "10000"),
    createFcPackagingOption("YOW4", Material.LAMINATED_PLASTIC, "5000")

    );
    */

    /**
     * Create fulfillment center packaging option from provided parameters.
     */
    private FcPackagingOption createFcPackagingOption(String fcCode, Material material,
                                                      String length, String width, String height) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);

        // Packaging packaging = new Packaging(material, new BigDecimal(length), new BigDecimal(width),
        //        new BigDecimal(height));

        // return new FcPackagingOption(fulfillmentCenter, packaging);

        Box box = new Box(material, new BigDecimal(length), new BigDecimal(width),
                new BigDecimal(height));

        return new FcPackagingOption(fulfillmentCenter, box);
    }

    // Method overloading with constructor for PolyBag
    private FcPackagingOption createFcPackagingOption(String fcCode, Material material, String volume) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);

        PolyBag polybag = new PolyBag(material, new BigDecimal(volume));

        return new FcPackagingOption(fulfillmentCenter, polybag);
    }

    //public List<FcPackagingOption> getFcPackagingOptions() {
    //    return fcPackagingOptions;
    //}

    public List<FcPackagingOption> getFcPackagingOptions() {
        List<FcPackagingOption> allOptions = new ArrayList<>();
        for (HashSet<FcPackagingOption> optionsSet : fcPackagingOptions.values()) {
            allOptions.addAll(optionsSet);
        }
        return allOptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FcPackagingOption that = (FcPackagingOption) o;
        return Objects.equals(that.getFulfillmentCenter(), that.getFulfillmentCenter()) &&
                Objects.equals(that.getPackaging(), that.getPackaging());
    }

    //@Override
    //public int hashCode() {
    //    return Objects.hash(getFulfillmentCenter(), getPackaging());
    //}

    @Override
    public int hashCode() {
        return Objects.hash(fcPackagingOptions);
    }

}
