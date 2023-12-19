package hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class SherlockandtheValidString {
    public static void main(String[] args) throws IOException {

         System.out.println(isValid("aabbccddeefghi"));
        System.out.println(isValid("abcdefghhgfedecba"));
        System.out.println(isValid("aabbc"));

        main2(null);
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result =  isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
    public static String isValid(String s) {
        // Write your code here
       boolean b =  isValidString(s);
       return b?"YES":"NO";
    }

    private static boolean isValidString(String s) {
        Map<Character, Long> frequency = s.chars().mapToObj(x -> Character.valueOf((char) x)).collect(groupingBy(Function.identity(),
                counting()));
        System.out.println(frequency);
        if(isAllFrequencySame(frequency)){
            return true;
        }
        for(Character ch: frequency.keySet()){

            Map<Character, Long> newMap =   deleteChar(frequency,ch);
            if(isAllFrequencySame(newMap)){
                return true;
            }

        }

        return false;
    }

    private static Map<Character, Long> deleteChar(Map<Character, Long> frequency, Character ch) {
        HashMap<Character, Long> map = new HashMap<>(frequency);
        Long count = map.get(ch);
        if(count==1){
            map.remove(ch);
        }else{
            map.put(ch,count-1);
        }
        return map;
    }

    private static boolean isAllFrequencySame(Map<Character, Long> frequency) {
        ArrayList<Map.Entry<Character, Long>> list = new ArrayList<>(frequency.entrySet());
        for(int i=1;i<list.size();i++){
            if(list.get(i).getValue().longValue()!= list.get(0).getValue().longValue()){
                return false;
            }
        }
        return true;
    }


}
