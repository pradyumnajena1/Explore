package LLD.carrental;

import LLD.carrental.enums.VehicleStatus;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private String licenseNumber;
    private boolean hasSunroof;
    private VehicleStatus status = VehicleStatus.AVAILABLE;
    private String make;
    private String model;
    private int year;
    private int passengerCapacity;
    private int mileage;
    private String fuelType;
    private ParkingStall parkingStall;
    private List<VehicleLog> vehicleLogs = new ArrayList<VehicleLog>();

    public Vehicle(String licenseNumber, boolean hasSunroof, String make, String model, int year, int passengerCapacity, int mileage, String fuelType) {}
    public void addLog(VehicleLog log) {}
}
