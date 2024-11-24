package LLD.carrental;

public class Truck extends Vehicle{
    private String type;

    public Truck(String licenseNumber, boolean hasSunroof, String make, String model, int year, int passengerCapacity, int mileage, String fuelType, String type) {
        super(licenseNumber, hasSunroof, make, model, year, passengerCapacity, mileage, fuelType);
        this.type = type;
    }
}
