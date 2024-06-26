package epp.string.revision;

import java.util.Map;

public class RomanToDecimal {
  public static void main(String[] args) {
    System.out.println(convertRomanToDecimal("LIX"));
    System.out.println(convertRomanToDecimal("LVIIII"));
    System.out.println(convertRomanToDecimal("XXXXXIIIIIIIII"));
  }

  private static int convertRomanToDecimal(String romanString) {
    Map<Character, Integer> mapping =
        Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000);
    Map<String, Integer> exceptions =
        Map.of(
            "IV", 4,
            "IX", 9,
            "XL", 40,
            "XC", 90,
            "CD", 400,
            "CM", 900);
    //return convertRomanToDecimal(mapping,exceptions, romanString);
    return convertRomanToDecimal(mapping, romanString);
  }

  private static int convertRomanToDecimal(
      Map<Character, Integer> mapping, Map<String, Integer> exceptions, String romanString) {
    if (romanString.length() == 0) {
      return 0;
    }
    if (romanString.length() == 1) {
      return mapping.get(romanString.charAt(0));
    }
    if(romanString.length()>=2){
        String prefix = romanString.substring(0, 2);
        if (exceptions.containsKey(prefix)) {
            return exceptions.get(prefix) + convertRomanToDecimal(romanString.substring(2));
        }
    }

    return mapping.get(romanString.charAt(0)) + convertRomanToDecimal(romanString.substring(1));
  }

  private static int convertRomanToDecimal(Map<Character, Integer> mapping, String romanString) {
    if (romanString.length() == 0) {
      return 0;
    }
    int value = mapping.get(romanString.charAt(romanString.length() - 1));
    for (int i = romanString.length() - 2; i >= 0; i--) {
      Integer current = mapping.get(romanString.charAt(i));
      Integer next = mapping.get(romanString.charAt(i + 1));
      if (current < next) {
        value -= current;
      } else {
        value += current;
      }
    }
    return value;
  }
}
