package epp.binarySearch;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class OptimumMailbox {
    public static void main(String[] args) {
        int[] residents = ArrayUtils.randomArray(10,1,5);
        int[] distances = ArrayUtils.randomUniqueArray(10,1,20);
        ArrayUtils.printArray(residents);
        ArrayUtils.printArray(distances);

        int mailbox = findOptimumMailboxIndex(residents,distances);
        System.out.println(mailbox);

    }

    private static int findOptimumMailboxIndex(int[] residents, int[] distances) {
        House[] houses = new House[residents.length];
        for(int i=0;i<houses.length;i++){
            houses[i] = new House(distances[i],residents[i]);
        }
        Arrays.sort(houses);
        System.out.println(Arrays.toString(houses));

        int totalResidents = getTotalResidents(houses);
        int median = totalResidents/2;
        System.out.println(median);
        int numResidents = 0;
        int index;
        for(index=0;index<houses.length;index++){
            numResidents+=houses[index].residents;
            System.out.println(houses[index]+" "+numResidents);

            if(numResidents>=median){
                break;
            }
        }


        return houses[index].distance;
    }

    private static int getTotalResidents(House[] houses) {
        int sum =0;
        for(House house:houses){
              sum += house.residents;
        }
        return sum;
    }


    private static class House implements Comparable<House>{
        int distance;
        int residents;

        public House(int distance, int residents) {
            this.distance = distance;
            this.residents = residents;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("House{");
            sb.append("distance=").append(distance);
            sb.append(", residents=").append(residents);
            sb.append('}');
            return sb.toString();
        }

        @Override
        public int compareTo(House o) {
            return Integer.compare(this.distance,o.distance);
        }
    }

}
