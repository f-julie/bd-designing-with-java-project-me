package com.amazon.ata.cost;

import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;

public class CarbonCostStrategy implements CostStrategy {
    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        BigDecimal carbonCost;
        BigDecimal mass = shipmentOption.getPackaging().getMass();
        switch (shipmentOption.getPackaging().getMaterial()) {
            case CORRUGATE:
                carbonCost = mass.multiply(BigDecimal.valueOf(0.017));
                break;
            case LAMINATED_PLASTIC:
                carbonCost = mass.multiply(BigDecimal.valueOf(0.012));
                break;
            default:
                throw new IllegalArgumentException("Invalid material");
        }
        return new ShipmentCost(shipmentOption, carbonCost);
    }
}
