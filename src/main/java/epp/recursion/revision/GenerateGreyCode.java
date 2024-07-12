package epp.recursion.revision;

import epp.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class GenerateGreyCode {
    public static void main(String[] args) {
        int numBits = 10;
        List<Integer> greyCodes = generateGreyCodes(numBits);
        System.out.println(greyCodes);
        for(int i:greyCodes){
            System.out.println(StringUtils.getBinaryString(i, numBits));
        }

    }

    private static List<Integer> generateGreyCodes(int numBits) {
        if(numBits==0){
            return new ArrayList<>(List.of(0));
        }
        List<Integer> previous = generateGreyCodes(numBits - 1);
        int leadingBitOne = 1<< (numBits - 1);
        for(int i=previous.size()-1;i>=0;i--) {
            previous.add(previous.get(i) | leadingBitOne);
        }
       return previous;
    }

    }
