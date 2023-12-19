package epp.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreyCode {
    public static void main(String[] args) {
        List<String> greyCodes = generateGreyCodes(3);
        System.out.println(greyCodes);
    }

    private static List<String> generateGreyCodes(int n) {
        if(n==1){
            return List.of("0","1");
        }
        List<String> codes = generateGreyCodes(n - 1);
        List<String> newCodes = new ArrayList<>();
        newCodes.addAll(getZeroCodes(codes));
        newCodes.addAll(getOneCodes(codes));
        return newCodes;
    }

    private static List<String> getOneCodes(List<String> codes) {
        List<String> codesForDigit = getCodesForDigit(codes, "1");
        Collections.reverse(codesForDigit);
        return codesForDigit;
    }

    private static List<String> getZeroCodes(List<String> codes) {
        return getCodesForDigit(codes, "0");
    }

    private static List<String> getCodesForDigit(List<String> codes, String digit) {
        List<String> result = new ArrayList<>();
        for(String code: codes){
            result.add(digit +code);
        }
        return result;
    }
}
