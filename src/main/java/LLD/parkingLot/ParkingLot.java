package LLD.parkingLot;

import java.util.List;

public class ParkingLot {
    private String name;
    private Address address;
    private List<ParkingFloor> parkingFloors;
    private List<EntranceGate> entrances;
    private List<ExitGate> exitGates;

    public   boolean isParkingSpaceAvailable(Vehicle vehicle){
        return false;
    }
    public boolean updateAttendant(Gate gate, ParkingAttendant attendant){
        return false;
    }


}

