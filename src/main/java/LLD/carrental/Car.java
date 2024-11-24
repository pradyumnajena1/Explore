package LLD.carrental;

import LLD.carrental.enums.CarType;

public class Car extends Vehicle{
    public Car(String licenseNumber, boolean hasSunroof, String make, String model, int year, int passengerCapacity, int mileage, String fuelType, CarType type) {
        super(licenseNumber, hasSunroof, make, model, year, passengerCapacity, mileage, fuelType);
        this.type = type;
    }

    private CarType type;

}
