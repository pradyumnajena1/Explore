package hackerrank.hard;

import epp.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class MorganAndAString {
    public static void main(String[] args) throws IOException {
        System.out.println(morganAndString2("ACA", "BCF"));
        System.out.println(morganAndString2("JACK", "DANIEL"));
        System.out.println(morganAndString2("ABACABA", "ABACABA"));
        main2(null);
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String a = bufferedReader.readLine();

                String b = bufferedReader.readLine();

                String result = morganAndString2(a, b);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static String morganAndString(String a, String b) {
        // Write your code here
        String result = "";
        Map<Pair<Integer, Integer>, String> cache = new HashMap<>();

        result = morganAndString(a, 0, b, 0, result, cache);
        return result;
    }

    private static String morganAndString(String a, int aIndex, String b, int bIndex, String result, Map<Pair<Integer, Integer>, String> cache) {

        if (aIndex == a.length()) {
            return result + b.substring(bIndex);
        }
        if (bIndex == b.length()) {
            return result + a.substring(aIndex);
        }
        Pair<Integer, Integer> key = new Pair<>(aIndex, bIndex);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        String value = null;
        if (a.charAt(aIndex) < b.charAt(bIndex)) {
            value = morganAndString(a, aIndex + 1, b, bIndex, result + a.charAt(aIndex), cache);
        } else if (a.charAt(aIndex) > b.charAt(bIndex)) {
            value = morganAndString(a, aIndex, b, bIndex + 1, result + b.charAt(bIndex), cache);
        } else {
            String partial1 = morganAndString(a, aIndex + 1, b, bIndex, result + a.charAt(aIndex), cache);
            String partial2 = morganAndString(a, aIndex, b, bIndex + 1, result + b.charAt(bIndex), cache);
            if (partial1.compareTo(partial2) <= 0) {
                value = partial1;
            } else {
                value = partial2;
            }
        }
        cache.put(key, value);
        return value;

    }


    public static String morganAndString2(String a, String b) {

        List<List<Pair<Character, Pair<Integer, Integer>>>> dp = new ArrayList<>();

        for (int i = 0; i <= a.length(); i++) {
            dp.add(new ArrayList<>());
            for (int j = 0; j <= b.length(); j++) {
                dp.get(i).add(null);
            }
        }
        for (int i = a.length(); i >= 0; i--) {
            for (int j = b.length(); j >= 0; j--) {

                if (i == a.length() && j == b.length()) {
                    dp.get(i).set(j, new Pair<>(null, null));
                } else if (i == a.length()) {
                    dp.get(i).set(j, new Pair<>(b.charAt(j), new Pair<>(i, j + 1)));

                } else if (j == b.length()) {
                    dp.get(i).set(j, new Pair<>(a.charAt(i), new Pair<>(i + 1, j)));

                } else {
                    if (a.charAt(i) < b.charAt(j)) {
                        dp.get(i).set(j, new Pair<>(a.charAt(i), new Pair<>(i + 1, j)));

                    } else if (a.charAt(i) > b.charAt(j)) {
                        dp.get(i).set(j, new Pair<>(b.charAt(j), new Pair<>(i, j + 1)));

                    } else {
                        if (compareString(dp, new Pair<>(i, j + 1), new Pair<>(i + 1, j)) < 0) {
                            dp.get(i).set(j, new Pair<>(a.charAt(i), new Pair<>(i, j + 1)));

                        } else {
                            dp.get(i).set(j, new Pair<>(b.charAt(j), new Pair<>(i + 1, j)));

                        }
                    }
                }


            }
        }
         /* for(int i=0;i< dp.size();i++){
              System.out.println(dp.get(i));
          }*/
        return getString(dp, new Pair<>(0, 0));


    }

    private static String getString(List<List<Pair<Character, Pair<Integer, Integer>>>> dp,
                                    Pair<Integer, Integer> point) {
        String s = "";
        Pair<Integer, Integer> current = point;
        while (current != null) {
            Character character = dp.get(current.getFirst()).get(current.getSecond()).getFirst();
            if (character == null) {
                break;
            }
            s = s + character;
            current = dp.get(current.getFirst()).get(current.getSecond()).getSecond();
        }
        return s;
    }

    private static int compareString(List<List<Pair<Character, Pair<Integer, Integer>>>> dp,
                                     Pair<Integer, Integer> point1, Pair<Integer, Integer> point2) {

        while (point1 != null && point2 != null) {
            Character character1 = dp.get(point1.getFirst()).get(point1.getSecond()).getFirst();
            Character character2 = dp.get(point2.getFirst()).get(point2.getSecond()).getFirst();
            System.out.println(character1+" "+character2);
            if (character1 == null && character2 == null) {
                return 0;
            } else if (character1 == null) {
                return -1;
            } else if (character2 == null) {
                return 1;


            } else {
                point1 = dp.get(point1.getFirst()).get(point1.getSecond()).getSecond();
                point2 = dp.get(point2.getFirst()).get(point2.getSecond()).getSecond();
            }

        }
        if (point1 == null) {
            return -1;
        } else if (point2 == null) {
            return 1;
        }
        return 0;
    }
}
