package epp.hashmap;

import java.util.*;

public class NearestRepeatedWords {

    public static void main(String[] args) {
        String paragraphText = "All work and no play     makes for no work no fun and no results";
        String[] paragraph = paragraphText.split("\\s+");
        System.out.println(Arrays.toString(paragraph));
        String repeatedWord = findNearestRepeatedWords(paragraph);
        System.out.println(repeatedWord);
    }

    private static String findNearestRepeatedWords(String[] paragraph) {
        Map<String,List<Integer>> wordPositionMap = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;
        String repeatWord = null;
        for(int i=0;i<paragraph.length;i++){
            List<Integer> positions = wordPositionMap.getOrDefault(paragraph[i], new ArrayList<>());
            if(positions.size()>0 ){
                Integer lastPosition = positions.get(positions.size() - 1);
                int distance = i - lastPosition;
                if(distance < minDistance){
                   repeatWord = paragraph[i];
                   minDistance = distance;
               }
            }
            positions.add(i);
            wordPositionMap.put(paragraph[i],positions);

        }
        return repeatWord;
    }
}
