package hackerrank.medium;

import java.util.ArrayList;
import java.util.List;

public class GoodlandElectricity {
    public static void main(String[] args) {
           System.out.println(pylons(2, new ArrayList<>(List.of(0, 1, 1, 1, 1, 0))));
            System.out.println(pylons(2, new ArrayList<>(List.of(0, 1, 0, 0, 0, 1, 0))));
        System.out.println(pylons(3, new ArrayList<>(List.of(0, 1, 0, 0, 0, 1, 1, 1, 1, 1))));
    }

    public static int pylons(int k, List<Integer> arr) {
        // Write your code here
        List<Integer> lastOneSeen = new ArrayList<>();
        int lastOne = -1;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == 1) {
                lastOneSeen.add(i);
                lastOne = i;
            } else {
                lastOneSeen.add(lastOne);
            }
        }
        //System.out.println(lastOneSeen);
        return pylons(k, arr, 0, arr.size() - 1, lastOneSeen);
    }

    private static int pylons(int k, List<Integer> arr, int start, int end, List<Integer> lastOneSeen) {

        if (end - start + 1 <= 2 * k - 1) {
            int mid = (start + end) / 2;
            Integer left = lastOneSeen.get(mid);
            Integer right = lastOneSeen.get(end);
            if (left == -1 || left < start) {
                return -1;
            }
            if (right == -1 || right < mid) {
                return -1;
            }

            return arr.get(mid) == 1 ? 1 : 2;
        }
        int mid = start + k - 1;
        Integer left = lastOneSeen.get(mid);
        if (left >= start) {
            int pylons = pylons(k, arr, Math.min(end, left + k), end, lastOneSeen);
            if (pylons == -1) {
                return -1;
            }
            return 1 + pylons;
        }


        return -1;
    }
}
