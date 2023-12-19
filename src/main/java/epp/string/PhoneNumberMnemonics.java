package epp.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneNumberMnemonics {
    public static void main(String[] args) {
        Map<Integer,char[]> map = new HashMap<>();
        map.put(2,new char[]{'a','b','c'});
        map.put(3,new char[]{'d','e','f'});
        map.put(4,new char[]{'g','h','i'});
        map.put(5,new char[]{'j','k','l'});
        map.put(6,new char[]{'m','n','o'});
        map.put(7,new char[]{'p','q','r','s'});
        map.put(8,new char[]{'t','u','v'});
        map.put(9,new char[]{'w','x','y','z'});

        List<List<Character>> mnemonics = getMnemonics("2276696",map);
        System.out.println(mnemonics);
    }

    private static List<List<Character>> getMnemonics(String s, Map<Integer, char[]> map) {
        List<List<Character>> mnemonics = new ArrayList<>();
        for(char ch:s.toCharArray()){
            char[] alphabets = map.get(ch - '0');
            if(mnemonics.isEmpty()){
                for(char alphabet:alphabets){
                    List<Character> word = new ArrayList<>();
                    word.add(alphabet);
                    mnemonics.add(word);
                }
            }else{
                List<List<Character>> newMnemonics = new ArrayList<>();
                for(List<Character> mnemonic:mnemonics){
                    for(char alphabet:alphabets){
                        List<Character> newMnemonic = new ArrayList<>(mnemonic);
                        newMnemonic.add(alphabet);
                        newMnemonics.add(newMnemonic);
                    }
                }
                mnemonics = newMnemonics;
            }
        }
        return mnemonics;
    }
}
