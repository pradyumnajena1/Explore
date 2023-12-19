package epp.array.revision;

import java.util.ArrayList;
import java.util.List;

public class FindAllPrimes {
    public static void main(String[] args) {
        List<Integer> primes = findAllPrimes(100);
        System.out.println(primes);
    }

    private static List<Integer> findAllPrimes(int n) {
        List<Integer> result = new ArrayList<>();
        int[] values = new int[n-1];
        for(int i=0;i<n-1;i++){
            values[i] = i+2;
        }
        for(int value:values){

            if(value==0){
                continue;
            }
            int count=0;
            for(int j=2;j<= (int)Math.sqrt(value);j++){
                if(value%j==0){
                    count++;
                }
            }
            if(count==0){
                result.add(value);
            }
            for(int i=2;i<=n/value;i++){
                values[value*i-2] = 0;
            }
        }
        return result;
    }
}
