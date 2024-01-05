package guidetocompetitiveprogramming;

import epp.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PrimeFactors {
    public static void main(String[] args) {
        System.out.println(isPrime(17));
        System.out.println(primeFactors(23));
        System.out.println(primeFactors(36));
        System.out.println(primeFactors(30));
        System.out.println(numOfFactors(12));
        System.out.println(sumOfFactors(12));
        System.out.println(numPrimesLessThanEqual(100));
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int x = 2; x * x <= n; x++) {
            if (n % x == 0) return false;
        }
        return true;
    }

    public static List<Integer> primeFactors(int n) {
        List<Integer> primeFactors = new ArrayList<>();
        for (int x = 2; x * x <= n; x++) {
            while (n % x == 0) {
                primeFactors.add(x);
                n = n / x;
            }
        }
        if (n > 1) {
            primeFactors.add(n);
        }
        return primeFactors;
    }

    public static int numOfFactors(int n) {
        List<Pair<Integer, Integer>> primeWithPower = primeFactorsWithPower(n);
        return primeWithPower.stream().mapToInt(x -> x.getSecond() + 1).reduce(1, (x, y) -> x * y);

    }

    public static int sumOfFactors(int n) {
        List<Pair<Integer, Integer>> primeWithPower = primeFactorsWithPower(n);
        return primeWithPower.stream().mapToInt(x -> (int) ((Math.pow(x.getFirst(), x.getSecond() + 1) - 1) / (x.getFirst() - 1))).reduce(1, (x, y) -> x * y);
    }

    public static List<Pair<Integer, Integer>> primeFactorsWithPower(int n) {
        List<Integer> primeFactors = primeFactors(n);
        Map<Integer, Long> freq = primeFactors.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return freq.entrySet().stream().map(x -> new Pair<>(x.getKey(), x.getValue().intValue())).collect(Collectors.toList());
    }

    public static int numPrimesLessThanEqual(int n) {
        int[] sieve = new int[n + 1];
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (sieve[i] == 0) {
                count++;
                for (int u = 2 * i; u <= n; u = u + i) {
                    sieve[u] = i;
                }
            }
        }
        return count;
    }
}
