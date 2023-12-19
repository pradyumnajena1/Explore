package hackerrank.easy;

import leetcode.hard.SwimInWater;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SuperFunctionalStrings {
    public static void main(String[] args) throws IOException {
         /* System.out.println(superFunctionalStrings(new String(Files.readAllBytes(Paths.get("C:\\Users\\Pradyumna" +
                "\\IdeaProjects\\Explore\\src\\main\\resources\\SuperFunctionString")), StandardCharsets.UTF_8)));
 */
        System.out.println(superFunctionalStrings("ggggggggggggggghhhhhhhhhhhhhhhjjjjjjjjjjjjjjjkkkkkkkkkkkkkkkuuuuuuuuuuuuuuuyyyyyyyyyyyyyyyj"));

        TreeSet<Object> tree = new TreeSet<>();
        tree.add(2);
        tree.add(5);
        tree.add(9);
        System.out.println(tree.tailSet(2, true));
        System.out.println(tree.headSet(9, true));
    }

    public static int superFunctionalStrings(String s) {

        int totalF = 0;
        int base = 10;
        int mod=17;
        int n = s.length();
        ArrayList<Integer> power
                = new ArrayList<Integer>(n + 1);

        // Precompute the powers of the base modulo the mod
        for (int i = 0; i <= n; i++) {
            power.add(1);
        }
        for (int i = 1; i <= n; i++) {
            power.set(i, (power.get(i - 1) * base) % mod);
        }
        for (int width = 1; width <= s.length(); width++) {
           // System.out.println("width "+width);
            Set<Integer> hash_values
                    = new HashSet<>(n - width + 1);
            // Precompute the powers of the base modulo the mod

            Set<Character> uniqueChars = new HashSet<>();
            int current_hash = 0;
            for (int start = 0; start <= s.length() - width; start++) {

                if (start == 0) {
                    for (int i = 0; i < width; i++) {
                        uniqueChars.add(s.charAt(start + i));
                        current_hash
                                = (current_hash * base + s.charAt(start + i)) % mod;
                    }
                } else {
                    uniqueChars.remove(s.charAt(start-1));
                    uniqueChars.add(s.charAt(start + width-1));

                    current_hash = (current_hash - power.get(width - 1) * s.charAt(start - 1)) % mod;
                    current_hash = (current_hash * base + s.charAt(start +width-1)) % mod;
                }
               // System.out.println(start +" " +(start+ width-1)+" " + uniqueChars.size());
                if(!hash_values.contains(current_hash)){
                    int subStringF =( (int) Math.pow(width, uniqueChars.size())) % 1000000007;
                    totalF =( totalF + subStringF) % 1000000007;
                    hash_values.add(current_hash);
                }

            }

        }
        return totalF;
    }

    private static class SubString {
        String s;
        int start;
        int end;
        private int hash = -1;

        public SubString(String s, int start, int end) {
            this.s = s;
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SubString other = (SubString) o;

            int length = end - start + 1;
            int otherLength = other.end - other.start + 1;

            if (otherLength != length)
                return false;

            for (int i = 0; i < length; i++) {
                if (s.charAt(start + i) != other.s.charAt(other.start + i)) {
                    return false;
                }
            }

            return true;

        }


        @Override
        public int hashCode() {
            if (hash != -1) {
                return hash;
            }
            int result = 1;
            for (int i = start; i <= end; i++) {
                result = 31 * result + s.charAt(i);
            }


            return hash = result;

        }

        @Override
        public String toString() {
            return new StringJoiner(", ", SubString.class.getSimpleName() + "[", "]")
                    .add("start=" + start)
                    .add("end=" + end)
                    .toString();
        }

        public void setHashcode(int hash) {
            this.hash = hash;
        }
    }


}
