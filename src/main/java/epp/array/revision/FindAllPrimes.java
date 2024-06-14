package epp.array.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllPrimes {
    public static void main(String[] args) {
        List<Integer> primes = findAllPrimes(100);
        System.out.println(primes);
    }

    private static List<Integer> findAllPrimes(int n) {
        List<Integer> result = new ArrayList<>();
        boolean[] candidates = new boolean[n+1];
        Arrays.fill(candidates,2,candidates.length,true);
        for(int i=2;i<=n;i++){
            if(candidates[i]){
                result.add(i);

                for(int j=i;j<=n;j+=i){
                    candidates[j] = false;
                }
            }

        }
        return result;
    }
}
