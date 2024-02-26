package com.amazon.ata.types;

import java.math.BigDecimal;

public class PolyBag extends Packaging {
    private BigDecimal volume;
    /**
     * The material this packaging is made of.
     */
    private Material laminatedPlastic;
    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     */
    public PolyBag(Material material, BigDecimal volume) {
        super(material);
        this.volume = volume;
    }

    @Override
    public boolean canFitItem(Item item) {
        return super.canFitItem(item);
    }

    @Override
    public BigDecimal getMass() {
        double mass = Math.ceil(Math.sqrt(volume.doubleValue()) * 0.6);
        return BigDecimal.valueOf(mass);
    }

    public BigDecimal getVolume() {
        return volume;
    }
}
