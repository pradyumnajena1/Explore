package LLD.carrental;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleInventory implements Search {
    private Map<String, List<Vehicle>> vehicleTypeIndex = new HashMap<String, List<Vehicle>>();
    private Map<String, List<Vehicle>> vehicleMakeIndex = new HashMap<String, List<Vehicle>>();

    @Override
    public List<Vehicle> searchByType(String type) {
        return null;
    }

    @Override
    public List<Vehicle> searchByModel(String model) {
        return null;
    }
}
