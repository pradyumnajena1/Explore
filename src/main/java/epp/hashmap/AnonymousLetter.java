package epp.hashmap;

import java.util.Map;

public class AnonymousLetter {
    public static void main(String[] args) {
        String letter = "level";
        String magazine = "lleevv";
        boolean feasible = checkAnonymousLetterConstruction(letter,magazine);
        System.out.println(feasible);
    }

    public static boolean checkAnonymousLetterConstruction(String letter, String magazine) {
        Map<Character, Integer> frequencyMap_letter = MapUtils.getFrequencyMap(letter);
        Map<Character, Integer> frequencyMap_magazine = MapUtils.getFrequencyMap(magazine);
        for(Map.Entry<Character, Integer> entry:frequencyMap_letter.entrySet()){
            if(entry.getValue()> frequencyMap_magazine.get(entry.getKey())){
                return false;
            }
        }
        return true;
    }
}
