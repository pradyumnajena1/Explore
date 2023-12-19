package epp.hashmap;

import java.util.*;

public class SmallestSubArrayContainingAllValues {
    public static void main(String[] args) {
        String paragraphText = "My paramount object in this struggle is to save the union and is not either to save " +
                "or destroy slavery";
        String[] paragraph = paragraphText.split("\\s+");
        String[] queries = new String[]{"union","save" };
        String[] smallestSubArray = getSmallestSubArray(paragraph,queries,true);
        System.out.println(Arrays.toString(smallestSubArray));

        smallestSubArray = getSmallestSubArray(paragraph,queries,false);
        System.out.println(Arrays.toString(smallestSubArray));


    }


    private static String[] getSmallestSubArray(String[] paragraph, String[] queries,boolean order) {
        Map<String, Integer> wordPositionMap = new HashMap<>();
        Set<String> querySet = new HashSet<>(Arrays.asList(queries));
        List<String> list = new ArrayList<>();

        int minDistance = Integer.MAX_VALUE;
        int minPosition = -1;
        for(int i=0;i<paragraph.length;i++){
            String currentWord = paragraph[i];
            if(querySet.contains( currentWord)){
               list.remove(currentWord);
                wordPositionMap.put(currentWord,i);
                list.add(currentWord);

                if(wordPositionMap.size()==querySet.size()){
                    if(!order || isSorted(wordPositionMap,queries)){
                        int min = wordPositionMap.get(list.get(0));
                        int distance = i-min;
                        if(distance<minDistance){
                            minDistance = distance;
                            minPosition = i;
                        }
                    }

                }
            }


        }
        String[] result = new String[minDistance+1];
        System.arraycopy(paragraph,minPosition-minDistance,result,0,minDistance+1);
        return result;
    }

    private static boolean isSorted(Map<String, Integer> wordPositionMap, String[] queries) {
        int lastValue = wordPositionMap.get(queries[0]);
        for(int i=1;i<queries.length;i++){
            int current = wordPositionMap.get(queries[i]);
            if(current<lastValue){
                return false;
            }
            lastValue = current;
        }
        return true;
    }


}
