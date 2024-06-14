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
        Map<Character, Long> charFrequency_magazine = MapUtils.getCharFrequency(magazine);

        for (Map.Entry<Character, Long> entry : charFrequency_letter.entrySet()) {
            if (entry.getValue() > charFrequency_magazine.getOrDefault(entry.getKey(), 0L)) {
                return false;
            }

        }
        return true;
    }
}
