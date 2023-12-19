package hackerrank.easy;

import epp.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeadOrnaments {
    public static void main(String[] args) {
        System.out.println(beadOrnaments(new ArrayList<>(List.of(2, 1))));
        System.out.println(beadOrnaments(new ArrayList<>(List.of(2, 2))));
        System.out.println(beadOrnaments(new ArrayList<>(List.of(4))));
        System.out.println(beadOrnaments(new ArrayList<>(List.of(3, 1))));
        System.out.println(beadOrnaments(new ArrayList<>(List.of(1, 1, 1, 1, 1))));
    }

    public static int beadOrnaments(List<Integer> b) {
        // Write your code here
        int numColors = b.size();
        int totalNumBeads = factorial(numColors);
        for (int i = 0; i < b.size(); i++) {
            totalNumBeads = totalNumBeads * factorial(b.get(i)) % 1000000007;
        }
        return totalNumBeads;
    }



    public static int nCr(int n, int r) {
        if (r > n) return 0;
        if (r == 0 || r == n) return 1;
        return nCr(n - 1, r - 1) + nCr(n - 1, r);
    }

    static int factorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact = fact * i % 1000000007;
        }
        return fact;
    }
}
