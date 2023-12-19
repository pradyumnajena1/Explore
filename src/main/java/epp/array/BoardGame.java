package epp.array;

import java.util.HashMap;
import java.util.Map;

public class BoardGame {

    public static void main(String[] args) {
        boolean winnable = isWinnable(new int[]{3,3,1,0,2,0,0});
        System.out.println(winnable);
    }

    private static boolean isWinnable(int[] game) {
        Map<Integer,Boolean> cache = new HashMap<>();
        return isWinnableRecurse(cache,game,0);
    }

    private static boolean isWinnableRecurse(Map<Integer, Boolean> cache, int[] game,int index) {
        if(index>=game.length){
            return true;
        }
        if(cache.get(index)!=null){
            return cache.get(index);
        }
        boolean result = false;
        for(int i=1;i<=game[index];i++){
            result = result || isWinnableRecurse(cache,game,index+i);
        }
        cache.put(index,result);
        return result;
    }
}
