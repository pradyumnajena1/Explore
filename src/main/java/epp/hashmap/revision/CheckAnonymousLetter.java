package epp.hashmap.revision;

import epp.hashmap.MapUtils;
import epp.utils.StringUtils;

import java.util.Map;

public class CheckAnonymousLetter {
    public static void main(String[] args) {
        String letter = "level";
        String magazine = "llvee";
        boolean isAnonymousLetterPossible = isAnonymousLetterPossible(letter, magazine);
        System.out.println(isAnonymousLetterPossible);
    }

    public static boolean isAnonymousLetterPossible(String letter, String magazine) {
        if (letter == null || magazine == null) {
            throw new IllegalArgumentException("letter or magazine cant be null");
        }

        if (letter.length() > magazine.length()) {
            return false;
        }

        Map<Character, Long> charFrequency_letter = MapUtils.getCharFrequency(letter);
        for(char c: magazine.toCharArray()){
            if(charFrequency_letter.containsKey(c)){
                charFrequency_letter.put(c,charFrequency_letter.get(c)-1);
                if(charFrequency_letter.get(c)==0){
                    charFrequency_letter.remove(c);
                }
                if(charFrequency_letter.isEmpty()){
                    return true;
                }
            }
        }
        return false;
    }
}
