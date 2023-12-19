package epp.greedy;

import java.util.Arrays;

public class FindAmpleCity {
    private static class GasStation{
        String name;
        int capacity;
        int nextDistance;

        public GasStation(String name, int capacity, int nextDistance) {
            this.name = name;
            this.capacity = capacity;
            this.nextDistance = nextDistance;
        }
    }
    public static void main(String[] args) {
        GasStation[] gasStations = new GasStation[]{
                new GasStation("A",20,300),
                new GasStation("B",15,400),
                new GasStation("C",15,1000),
                new GasStation("D",15,300),
                new GasStation("E",35,300),
                new GasStation("F",25,600),
                new GasStation("G",30,400),
                new GasStation("H",15,1100),
                new GasStation("I",65,400),
                new GasStation("J",45,1000),
                new GasStation("K",10,200),
                new GasStation("L",45,300),
                new GasStation("M",25,300)
        };
        Integer totalCapacity = Arrays.stream(gasStations).map(x -> x.capacity).reduce(0, (a, b) -> a + b);
        Integer totalDistance = Arrays.stream(gasStations).map(x -> x.nextDistance).reduce(0, (a, b) -> a + b);
        System.out.println(totalCapacity);
        System.out.println(totalDistance);
    }
}
