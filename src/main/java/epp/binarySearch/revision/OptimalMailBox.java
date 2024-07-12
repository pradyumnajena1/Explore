package epp.binarySearch.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

public class OptimalMailBox {

    public static void main(String[] args) {
        int[] distances = new int[]{10,12,14,38,192};
        int[] people = new int[]{5,3,2,7,2};
    Building[] buildings = new Building[] {new Building(10,5),new Building(12,3),
            new Building(14,2),new Building(38,7),new Building(192,2)};
        int mailBoxIndex = getOptimalMailBoxIndex(buildings);
        System.out.println(mailBoxIndex);
        mailBoxIndex = getOptimalMailBoxIndex2(buildings);
        System.out.println(mailBoxIndex);
    }

    private static int getOptimalMailBoxIndex(Building[] buildings ) {
        if(buildings==null||buildings.length==0 ){
            throw new IllegalArgumentException("values are null");
        }
        int[] peopleLeft = new int[buildings.length];
        peopleLeft[0] = buildings[0].people;
        for(int i=1;i<peopleLeft.length;i++){
            peopleLeft[i] = buildings[i].people+peopleLeft[i-1];
        }
        int[] peopleRight = new int[buildings.length];
        peopleRight[buildings.length-1] = buildings[buildings.length-1].people;
        for(int i=peopleRight.length-2;i>=0;i--){
            peopleRight[i] = buildings[i].people+peopleRight[i+1];
        }
        ArrayUtils.printArray(peopleLeft);
        ArrayUtils.printArray(peopleRight);
        int[] totalDistance  = new int[buildings.length];
        for(int i=0;i<buildings.length;i++){
            totalDistance[0] += buildings[i].people*(buildings[i].distance-buildings[0].distance);
        }
        int minDistance = totalDistance[0];
        int minIndex = 0;
        for(int i=1;i<buildings.length;i++){
            totalDistance[i] = totalDistance[i-1] +
                    (peopleLeft[i-1]*(buildings[i].distance-buildings[i-1].distance))
                    -  (peopleRight[i]*(buildings[i].distance-buildings[i-1].distance));
            if(totalDistance[i]<minDistance){
                minDistance = totalDistance[i];
                minIndex = i;
            }
        }
        ArrayUtils.printArray(totalDistance);
        return minIndex;
    }

    private static int getOptimalMailBoxIndex2(Building[] buildings) {
        if(buildings==null||buildings.length==0 ){
            throw new IllegalArgumentException("values are null");
        }
        Arrays.sort(buildings, Comparator.comparingInt(Building::getDistance));
        int totalResidents = Arrays.stream(buildings).mapToInt(Building::getPeople).sum();
        return findBuildingForResident(totalResidents/2,buildings);

    }

    private static int findBuildingForResident(int median, Building[] buildings) {
        int sum =0;
        int index = 0;
        for(int i=0;i<buildings.length;i++){
            sum+=buildings[i].people;
            if(sum>=median){
                index = i;
                break;
            }
        }
        return index ;
    }

    private static class Building{
        int distance;
        int people;

        public Building(int distance, int people) {
            this.distance = distance;
            this.people = people;
        }

        public int getDistance() {
            return distance;
        }

        public int getPeople() {
            return people;
        }
    }
}
