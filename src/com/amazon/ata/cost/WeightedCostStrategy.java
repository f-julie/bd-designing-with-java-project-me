package com.amazon.ata.cost;

import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;

public class WeightedCostStrategy implements CostStrategy {
    private double monetaryWeight;
    private double carbonWeight;
    private MonetaryCostStrategy monetaryCostStrategy;
    private CarbonCostStrategy carbonCostStrategy;

    public WeightedCostStrategy(double monetaryWeight, double carbonWeight) {
        this.monetaryWeight = monetaryWeight;
        this.carbonWeight = carbonWeight;
        this.monetaryCostStrategy = new MonetaryCostStrategy();
        this.carbonCostStrategy = new CarbonCostStrategy();
    }

    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        BigDecimal monetaryCost = monetaryCostStrategy.getCost(shipmentOption).getCost();
        BigDecimal carbonCost = carbonCostStrategy.getCost(shipmentOption).getCost();
        BigDecimal totalCost = monetaryCost.multiply(BigDecimal.valueOf(monetaryWeight))
                .add(carbonCost.multiply(BigDecimal.valueOf(carbonWeight)));

        return new ShipmentCost(shipmentOption, totalCost);
    }
}
