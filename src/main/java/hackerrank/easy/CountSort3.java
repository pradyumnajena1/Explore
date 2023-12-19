package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;

public class CountSort3 {
    public static void main(String[] args) {
        ArrayList<List<String>> arr = new ArrayList<>();
        arr.add(new ArrayList<>(List.of("0","a")));
        arr.add(new ArrayList<>(List.of("1","b")));
        arr.add(new ArrayList<>(List.of("0","c")));
        arr.add(new ArrayList<>(List.of("1","d")));
        countSort(arr);
    }

    public static void countSort(List<List<String>> arr) {
        // Write your code here
        List<List<String>> frequency = new ArrayList<>();
        for(int i=0;i< arr.size();i++){
            frequency.add(new ArrayList<>());
        }
        for(int i=0;i<arr.size();i++){
            List<String> term = arr.get(i);
            int key = Integer.parseInt( term.get(0));
            String  value =   term.get(1);

            if(i< arr.size()/2){
                frequency.get(key).add("-");
            }else{

                frequency.get(key).add(value);
            }
        }
        List<String> result = new ArrayList<>();
        for(List<String> list:frequency){
            for(String s:list){
                result.add(s);
            }
        }
        System.out.println(getAsString(result));

    }

    private static String getAsString(List<String> arr){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<arr.size();i++){
            sb.append(arr.get(i));
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        String string = sb.toString();

        return string;
    }
}
