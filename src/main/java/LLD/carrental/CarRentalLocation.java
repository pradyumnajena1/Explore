package LLD.carrental;

import java.util.ArrayList;
import java.util.List;

public class CarRentalLocation {
    private String locationName;
    private Address address;
    private List<Vehicle> vehicles = new ArrayList<Vehicle>();

    public CarRentalLocation(String locationName, Address address) {
        this.locationName = locationName;
        this.address = address;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Address getAddress() {
        return address;
    }
}
