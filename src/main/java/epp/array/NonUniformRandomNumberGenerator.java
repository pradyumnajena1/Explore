package epp.array;

import java.util.HashMap;
import java.util.Map;

public class NonUniformRandomNumberGenerator {

    private int[] items;
    private double[] probabilities;

    public NonUniformRandomNumberGenerator(int[] items, double[] probabilities) {
        if(items.length!=probabilities.length){
            throw new IllegalArgumentException("Invalid probabilities");
        }
        this.items = items;

        this.probabilities = probabilities;
        for(int i=1;i<probabilities.length;i++){
            this.probabilities[i] = this.probabilities[i-1]+ this.probabilities[i];
        }
    }

    public int getNext(){
     double random =    Math.random();
     for(int i=0;i<probabilities.length;i++){
         if(random<=probabilities[i]){
             return items[i];
         }
     }
     throw new IllegalStateException("shd never reach here");
    }

    public static void main(String[] args) {
        NonUniformRandomNumberGenerator randomNumberGenerator = new NonUniformRandomNumberGenerator(new int[]{1,2,3,
                4},new double[]{0.2,0.3,0.3,0.2});
        Map<Integer,Integer> freqMap = new HashMap<>();
        for(int i=0;i<10000;i++){
            int next = randomNumberGenerator.getNext();
            freqMap.put(next,   freqMap.getOrDefault(next,0)+1);
        }
        System.out.println(freqMap);
    }
}
