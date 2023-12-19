package epp.dp.revision;

import epp.Pair;

import java.util.HashMap;
import java.util.Map;

public class ComputeEditDistance {
    public static void main(String[] args) {
        String correctWord = "INTENTION";
        String misspelledWord = "EXECUTION";
        int editDistance =computeEditDistance(correctWord,misspelledWord);
        System.out.println(editDistance);
    }

    private static int computeEditDistance(String correctWord, String misspelledWord) {
        return computeEditDistance(correctWord.toCharArray(),misspelledWord.toCharArray());
    }

    private static int computeEditDistance(char[] correct, char[] misspelled) {
        Map<Pair<Integer,Integer> ,Integer> cache = new HashMap<>();
        return computeEditDistance(correct,0,misspelled,0,cache);
    }

    private static int computeEditDistance(char[] correct, int correctIndex, char[] misspelled, int misspelledIndex, Map<Pair<Integer, Integer>, Integer> cache) {
        if(correctIndex==correct.length && misspelledIndex==misspelled.length){
            return 0;
        }
        if(correctIndex==correct.length){
            return misspelled.length -misspelledIndex;
        }
        if(misspelledIndex==misspelled.length){
            return correct.length - correctIndex;
        }
        Pair<Integer, Integer> key = new Pair<>(correctIndex, misspelledIndex);
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        int result;
        if(correct[correctIndex] == misspelled[misspelledIndex]){
            result = computeEditDistance(correct,correctIndex+1,misspelled,misspelledIndex+1,cache);


        } else{
            int delete = 1 + computeEditDistance(correct,correctIndex,misspelled,misspelledIndex+1,cache);
            int insert = 1 + computeEditDistance(correct,correctIndex+1,misspelled,misspelledIndex,cache);
            int replace = 1 + computeEditDistance(correct,correctIndex+1,misspelled,misspelledIndex+1,cache);

            result = Math.min(delete,insert);
            result = Math.min(replace,result);
        }
        cache.put(key,result);
        return result;
    }
}
