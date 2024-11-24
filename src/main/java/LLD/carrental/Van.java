package LLD.carrental;

import LLD.carrental.enums.VanType;

public class Van extends Vehicle{
    VanType vanType;

    public Van(String licenseNumber, boolean hasSunroof, String make, String model, int year, int passengerCapacity, int mileage, String fuelType, VanType vanType) {
        super(licenseNumber, hasSunroof, make, model, year, passengerCapacity, mileage, fuelType);
        this.vanType = vanType;
    }

    public VanType getVanType() {
        return vanType;
    }
}
