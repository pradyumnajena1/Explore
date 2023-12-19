package epp.recursion.revision;

import epp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GenerateGreyCode {
    public static void main(String[] args) {
        int value = 3;
        List<Integer> greyCodes = generateGreyCodes(value);
        System.out.println(greyCodes);
        for(int i:greyCodes){
            System.out.println(StringUtils.getBinaryString(i, 3));
        }

    }

    private static List<Integer> generateGreyCodes(int value) {
        if(value==1){
            ArrayList<Integer> result = new ArrayList<>();
            result.add(0);
            result.add(1);
            return result;
        }
        List<Integer> partial = generateGreyCodes(value - 1);
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0;i<partial.size();i++){
            result.add(partial.get(i)*2);
        }
        for(int i=partial.size()-1;i>=0;i--){
            result.add(partial.get(i)*2+1);
        }
        return result;
    }

    }
