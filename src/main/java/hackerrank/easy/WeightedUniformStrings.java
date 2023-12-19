package hackerrank.easy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class WeightedUniformStrings {
    public static void main(String[] args) throws IOException {
        System.out.println(weightedUniformStrings("abbcccdddd", new ArrayList<>(List.of(1, 7, 5, 4, 15))));
        System.out.println(weightedUniformStrings("abccddde", new ArrayList<>(List.of(6,1,3,12,5,9,10))));
        System.out.println(weightedUniformStrings(new String( Files.readAllBytes(Path.of("C:\\Users\\Pradyumna" +
                "\\IdeaProjects\\Explore\\src\\main\\resources\\WeightedUniformString.txt"))),
                new ArrayList<>(List.of(61541,
                        2057085,
                        954800,
                        33344,
                        1422762,
                        24048,
                        19068,
                        2164680,
                        895100,
                        11016,
                        976275,
                        844283,
                        1965652,
                        2057941,
                        751525,
                        571675,
                        339875,
                        335254,
                        57072,
                        2764023))));
    }

    public static List<String> weightedUniformStrings(String s, List<Integer> queries) {
        // Write your code here
        Set<Integer> weightedMap = new HashSet<>();
        char[] chars = s.toCharArray();
        char prev = chars[0];
        int runningLength = 1;
        int i=1;
        while(i<s.length()){
            if(chars[i]==prev){
                runningLength++;
            }else{
                weightedMap.add((prev-'a'+1)*runningLength);
                runningLength=0;
                prev=chars[i];
            }
            i++;
        }

        for( i=0;i<s.length(); ){
            int j=i;
            while (j<s.length() && s.charAt(i)==s.charAt(j)){
                int weight = (s.charAt(i) - 'a'+1) * (j - i + 1);

                weightedMap.add(weight );
                j++;
            }
            i=j;
        }
        System.out.println(weightedMap);
        List<String> result = new ArrayList<>();
        for(Integer q:queries){
            if(weightedMap.contains(q)){
                result.add("Yes");
            }else{
                result.add("No");
            }
        }
        return result;
    }
}
