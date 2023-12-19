package epp.binarySearch.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class OptimalMailBox {
    public static void main(String[] args) {
        int[] distances = new int[]{10,12,14,18,22};
        int[] people = new int[]{5,3,2,7,2};
        int mailBoxIndex = getOptimalMailBoxIndex(distances,people);
        System.out.println(mailBoxIndex);
        mailBoxIndex = getOptimalMailBoxIndex(distances,people);
        System.out.println(mailBoxIndex);
    }

    private static int getOptimalMailBoxIndex(int[] distances, int[] people) {
        if(distances==null||people==null||people.length!= distances.length){
            throw new IllegalArgumentException("values are null or lengths not matching");
        }
        int[] peopleLeft = new int[people.length];
        peopleLeft[0] = people[0];
        for(int i=1;i<people.length;i++){
            peopleLeft[i] = people[i]+peopleLeft[i-1];
        }
        int[] peopleRight = new int[people.length];
        peopleRight[people.length-1] = people[people.length-1];
        for(int i=people.length-2;i>=0;i--){
            peopleRight[i] = people[i]+peopleRight[i+1];
        }
        ArrayUtils.printArray(peopleLeft);
        ArrayUtils.printArray(peopleRight);
        int[] totalDistance  = new int[people.length];
        for(int i=0;i<people.length;i++){
            totalDistance[0] += people[i]*(distances[i]-distances[0]);
        }
        int minDistance = totalDistance[0];
        int minIndex = 0;
        for(int i=1;i<people.length;i++){
            totalDistance[i] = totalDistance[i-1] +
                    (peopleLeft[i-1]*(distances[i]-distances[i-1])) -  (peopleRight[i]*(distances[i]-distances[i-1]));
            if(totalDistance[i]<minDistance){
                minDistance = totalDistance[i];
                minIndex = i;
            }
        }
        ArrayUtils.printArray(totalDistance);
        return minIndex;
    }
    private static int getOptimalMailBoxIndex2(int[] distances, int[] people) {
        int totalResidents = Arrays.stream(people).sum();
        if(totalResidents%2==1){
            int median = totalResidents/2+1;
            return findBuildingForResident(median,people);
        }else{
            int mid1 = totalResidents/2;
            int mid2 = totalResidents/2+1;
            return findBuildingForResident(mid1,people);
        }
    }

    private static int findBuildingForResident(int median, int[] people) {
        int sum =0;
        int index = 0;
        for(int i=0;i<people.length;i++){
            sum+=people[i];
            if(sum>=median){
                index = i;
                break;
            }
        }
        return index ;
    }
}
