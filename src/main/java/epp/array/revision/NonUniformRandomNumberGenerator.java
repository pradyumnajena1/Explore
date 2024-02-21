package epp.array.revision;

import epp.array.ArrayUtils;

public class NonUniformRandomNumberGenerator {
    private final double[] cumulative;
    private double[] probabilities;
    private int[] values ;

    public NonUniformRandomNumberGenerator(double[] probabilities, int[] values) {
        this.probabilities = probabilities;
        this.cumulative = new double[probabilities.length];
        cumulative[0] = probabilities[0];
        for(int i=1;i<cumulative.length;i++){
            cumulative[i] = cumulative[i-1]+probabilities[i];
        }
        this.values = values;
    }
    public int getNext(){
        double rand = Math.random();
        ArrayUtils.printArray(cumulative);
        System.out.println(rand);
        int low = 0;
        int high = cumulative.length-1;
        int index = -1;
        while (low<=high){
            int mid = (low+high)/2;
            if(rand<= cumulative[mid] ){
                index = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }

        return values[index];
    }

    public static void main(String[] args) {
        int[] values= new int[]{1,2,3,4,5,6};
        double[] probabilities = new double[]{0.125,0.25,0.125,0.3,0.1,0.1};
        NonUniformRandomNumberGenerator generator = new NonUniformRandomNumberGenerator(probabilities,values);
        System.out.println(generator.getNext());
        System.out.println(generator.getNext());
        System.out.println(generator.getNext());
        System.out.println(generator.getNext());
        System.out.println(generator.getNext());
        System.out.println(generator.getNext());
    }
}
