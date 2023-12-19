package epp.array.revision;

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
        int index = 0;
        while (rand>cumulative[index]){
            index++;
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
