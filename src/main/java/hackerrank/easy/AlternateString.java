package hackerrank.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlternateString {
    public static void main(String[] args) {
        System.out.println(alternate("abaacdabd"));
        System.out.println(alternate("beabeefeab"));
        System.out.println(alternate("muqqzbcjmyknwlmlcfqjujabwtekovkwsfjrwmswqfurtpahkdyqdttizqbkrsmfpxchbjrbvcunogcvragjxivasdykamtkinxpgasmwz"));

    }

    public static int alternate(String s) {
        char[] charArray = s.toCharArray();
        Map<Character, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            List<Integer> list = indexMap.getOrDefault(c, new ArrayList<>());
            list.add(i);
            indexMap.put(c, list);

        }
        System.out.println(indexMap);
        int maxLength = 0;
        ArrayList<Character> characters = new ArrayList<>(indexMap.keySet());
        for (int i = 0; i < characters.size(); i++) {
            for (int j = i + 1; j < characters.size(); j++) {
                if (i != j) {

                    if (canFormAlternateString(indexMap, characters.get(i), characters.get(j))) {

                        int length = indexMap.get(characters.get(i)).size() + indexMap.get(characters.get(j)).size();
                        if (maxLength < length) {
                            maxLength = length;
                            System.out.println(characters.get(i) + " " + characters.get(j));
                        }
                        maxLength = Math.max(length, maxLength);
                    }
                }

            }
        }

        return maxLength;
    }

    private static boolean canFormAlternateString(Map<Character, List<Integer>> indexMap, char a, char b) {
        int aIndex = 0;
        int bIndex = 0;
        if (a == 'e' && b == 'h') {
            System.out.println();
        }
        List<Integer> alist = indexMap.get(a);
        List<Integer> blist = indexMap.get(b);
        if (Math.abs(alist.size() - blist.size()) > 1) {
            return false;
        }
        Integer lastSelected = null;
        while (aIndex < alist.size() && bIndex < blist.size()) {
            if (alist.get(aIndex) < blist.get(bIndex)) {
                aIndex++;
                if (lastSelected != null && lastSelected == 1) {
                    return false;
                }
                lastSelected = 1;
            } else {
                bIndex++;
                if (lastSelected != null && lastSelected == 2) {
                    return false;
                }
                lastSelected = 2;
            }
        }
        while (aIndex < alist.size()) {
            aIndex++;
            if (lastSelected != null && lastSelected == 1) {
                return false;
            }
            lastSelected = 1;
        }
        while (bIndex < blist.size()) {
            bIndex++;
            if (lastSelected != null && lastSelected == 2) {
                return false;
            }
            lastSelected = 2;
        }


        return true;
    }


}
