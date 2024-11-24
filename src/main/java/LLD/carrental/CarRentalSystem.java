package LLD.carrental;

import java.util.ArrayList;
import java.util.List;

public class CarRentalSystem {
    private List<CarRentalLocation> locations = new ArrayList<CarRentalLocation>();

    public void addLocation(CarRentalLocation location) {
        locations.add(location);
    }
}
