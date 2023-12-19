package hackerrank.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReverseShuffleMerge {
    public static void main(String[] args) {
        System.out.println(reverseShuffleMerge("eggegg"));
       /* System.out.println(reverseShuffleMerge("abab"));
        System.out.println(reverseShuffleMerge("abab"));
        System.out.println(reverseShuffleMerge("abcdefgabcdefg"));
        System.out.println(reverseShuffleMerge("aeiouuoiea"));
        System.out.println(reverseShuffleMerge("bdabaceadaedaaaeaecdeadababdbeaeeacacaba"));*/
    }

    public static String reverseShuffleMerge(String s) {
        // Write your code here
        Map<Character, Long> charFrequency = getCharFrequency(s);
        Map<Character, Long> halfcharFrequency =  getHalfCharFrequency(charFrequency);
        int start = 0;
        int end=0;
        HashMap<Character, Long> current = new HashMap<>();
        while (end<s.length()){
            while (end<s.length() &&  !contains(current,halfcharFrequency)){
                current.put(s.charAt(end),current.getOrDefault(s.charAt(end ),0l)+1);
            }
            while (start<end && contains(current,halfcharFrequency)){
                current.put(s.charAt(start),current.getOrDefault(s.charAt(start ),0l)-1);
                if(current.get(s.charAt(start))==0){
                    current.remove(s.charAt(start));
                }
            }
            System.out.println(s.substring(start,end));

        }
        return null;
    }

    private static boolean contains(HashMap<Character, Long> current, Map<Character, Long> halfcharFrequency) {
        for(Map.Entry<Character, Long> entry:current.entrySet()){
            if(entry.getValue()>halfcharFrequency.getOrDefault(entry.getKey(),0l)){
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Long> getHalfCharFrequency(Map<Character, Long> charFrequency) {
        Map<Character, Long> result = new HashMap<>();
        for(Map.Entry<Character, Long> entry:charFrequency.entrySet()){
            result.put(entry.getKey(),entry.getValue()/2);
        }
        return result;
    }

    private static Map<Character, Long> getCharFrequency(String b) {
        return b.chars().mapToObj(x -> Character.valueOf((char) x))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static String reverse(String s) {
        char[] charArray = s.toCharArray();
        reverse(charArray);
        return new String(charArray);
    }

    public static void reverse(char[] array) {
        int left = 0;
        int right = array.length - 1;
        reverse(array, left, right);
    }
    private static void reverse(char[] array, int left, int right) {
        while (left < right) {
            swap(array, left++, right--);
        }
    }
    public static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
