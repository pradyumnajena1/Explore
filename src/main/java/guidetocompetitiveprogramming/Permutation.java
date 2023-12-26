package guidetocompetitiveprogramming;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public static void main(String[] args) {
        generatePermutations(new ArrayList<>(List.of(1,2,3,4)));
    }

    private static void generatePermutations(List<Integer> values) {

        generatePermutations(values, 0);
    }

    private static void generatePermutations(List<Integer> values,   int i) {
        if(i==values.size()){
            System.out.println(values);
        }
        for(int  j=i;j<values.size();j++){
            swap(values,i,j);
            generatePermutations(values, i+1);
            swap(values,i,j);
        }
    }
    public static <T> void swap(List<T> values, int i, int j) {
        T temp = values.get(i);
        values.set(i, values.get(j));
        values.set(j, temp);
    }

}
