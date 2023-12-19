package epp.recursion;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SynthesizeExpression {

    private static class CacheKey{
        int start;
        int end;
        int expressionValue;

        public CacheKey(int start, int end, int expressionValue) {
            this.start = start;
            this.end = end;
            this.expressionValue = expressionValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CacheKey cacheKey = (CacheKey) o;
            return start == cacheKey.start && end == cacheKey.end && expressionValue == cacheKey.expressionValue;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end, expressionValue);
        }
    }
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,2,5,3,7,8,5,9};
        String expression = getExpression(array,995);
       // int[] array = new int[]{2,2,3};
       // String expression = getExpression(array,223);
        System.out.println(expression);
    }

    private static String getExpression(int[] array, int expressionValue) {
        Map<CacheKey,String> expressionCache = new HashMap<>();
        return getExpression(array,0,array.length-1,expressionValue,expressionCache );
    }

    private static String getExpression(int[] array, int start, int end, int expressionValue,
                                        Map<CacheKey,String> expressionCache) {
        CacheKey key = new CacheKey(start, end, expressionValue);
        if(expressionCache.containsKey(key)){
            return expressionCache.get(key);
        }
        if(start==end && expressionValue== getIntValue(array,start,end)){
            return  ""+array[start];
        }else if(start+1==end){
            int intValue = getIntValue(array, start, end);
            if(intValue ==expressionValue){
                return ""+intValue;
            }
            int left = getIntValue(array, start, start);
            int right = getIntValue(array, end, end);
            if(left+right ==expressionValue){
                return ""+left+"+"+right;
            }else if(left*right==expressionValue){
                return ""+left+"*"+right;
            }
            return null;

        }
        String expression = null;
      outer:  for(int i=start+1;i<end;i++){
            for(int value =0;value<=expressionValue;value++){

                String leftSumExpression = getExpression(array, start, i , value,expressionCache);
                String rightSumExpression = getExpression(array, i+1, end, expressionValue - value,expressionCache);
                if(leftSumExpression !=null && rightSumExpression !=null){
                      expression = leftSumExpression + "+" + rightSumExpression;
                       break outer;
                }
            }

            for(int value =1;value<=expressionValue;value++){
                if(expressionValue%value==0){

                    String leftMulExpression = getExpression(array, start, i , value,expressionCache);
                    String rightMulExpression = getExpression(array, i+1, end, expressionValue / value,expressionCache);

                    if(leftMulExpression !=null && rightMulExpression !=null){
                          expression = leftMulExpression + "*" + rightMulExpression;
                           break outer;
                    }
                }
            }
        }
        expressionCache.put(key,expression);
        return expression;
    }

    private static int getIntValue(int[] array,int start,int end) {
        String s = "";
        for(int i=start;i<=end;i++){
            s+=array[i];
        }
        return Integer.parseInt(s);
    }
}
