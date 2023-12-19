package hackerrank.medium;

import epp.array.ArrayUtils;

import java.util.HashSet;
import java.util.Set;

public class FlatlandSpaceStations {
    public static void main(String[] args) {
        System.out.println(flatlandSpaceStations(5, new int[]{0, 4}));
        System.out.println(flatlandSpaceStations(6, new int[]{0,1,2, 4,3,5}));
    }

    static int flatlandSpaceStations(int n, int[] c) {
        Set<Integer> stations = new HashSet<>();
        for (int i = 0; i < c.length; i++) {
            stations.add(c[i]);
        }
        int[] left = new int[n];
        int[] right = new int[n];
        int[] distance = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = stations.contains(i) ? 0 :( i > 0 ? left[i - 1] + 1 : Integer.MAX_VALUE);
        }

        for (int i = n-1; i >= 0; i--) {
            right[i] = stations.contains(i) ? 0 :( i < n - 1 ? right[i + 1] + 1 : Integer.MAX_VALUE);
        }
        ArrayUtils.printArray(left);
        ArrayUtils.printArray(right);
        int maxDistance = Integer.MIN_VALUE;
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Math.min(left[i], right[i]);
            maxDistance = Math.max(distance[i], maxDistance);
        }
        ArrayUtils.printArray(distance);

        return maxDistance;
    }
}
