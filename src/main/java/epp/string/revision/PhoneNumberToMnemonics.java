package epp.string.revision;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhoneNumberToMnemonics {
    public static void main(String[] args) {
        Map<Integer, List<Character>> mapping = Map.of(
                0,List.of(),
                1,List.of(),
                2,List.of('A','B','C'),
                3,List.of('D','E','F'),
                4,List.of('G','H','I'),
                5,List.of('J','K','L'),
                6,List.of('M','N','O'),
                7,List.of('P','Q','R','S'),
                8,List.of('T','U','V'),
                9,List.of('W','X','Y','Z')
        );
        List<String> mnemonics = getMnemonics(mapping, "2276696");
        System.out.println(mnemonics);
    }

    private static List<String> getMnemonics(Map<Integer, List<Character>> mapping, String number) {
       if(number.length()==0){
           return List.of("");
       }
       List<String> partialResults = getMnemonics(mapping,number.substring(1));
        List<String> result = new ArrayList<>();
       Integer digit = number.charAt(0)-'0';
       for(Character ch:mapping.get(digit)){
           for(String partialMnemonic:partialResults){

               result.add(ch + partialMnemonic);
           }
       }
        return result;
    }
}
