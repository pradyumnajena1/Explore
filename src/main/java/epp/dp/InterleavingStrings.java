package epp.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InterleavingStrings {
    public static void main(String[] args) {
        String s1="gtaa";
        String s2="atc";
        boolean check = isInterLeaving(s1,s2,"gattaca");
        System.out.println(check);

        check = isInterLeaving(s1,s2,"gatacta");
        System.out.println(check);
    }
    private static class Tupple{
        String s1;
        String s2;
        String result;

        public Tupple(String s1, String s2, String result) {
            this.s1 = s1;
            this.s2 = s2;
            this.result = result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tupple tupple = (Tupple) o;
            return Objects.equals(s1, tupple.s1) && Objects.equals(s2, tupple.s2) && Objects.equals(result, tupple.result);
        }

        @Override
        public int hashCode() {
            return Objects.hash(s1, s2, result);
        }
    }

    private static boolean isInterLeaving(String s1, String s2, String result) {
        Map<Tupple,Boolean> cache = new HashMap<>();
        return isInterLeaving2(s1, s2, result,cache);
    }

    private static boolean isInterLeaving2(String s1, String s2, String result, Map<Tupple, Boolean> cache) {
        if(result.isEmpty()  ){
            return s1.isEmpty() && s2.isEmpty();
        }
        if(s1.isEmpty() && s2.isEmpty()){
            return result.isEmpty();
        }
        if(s1.isEmpty()){
            return s2.equals(result);
        }
        if(s2.isEmpty()){
            return s1.equals(result);
        }
        Tupple key = new Tupple(s1, s2, result);
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        boolean check = false;
        if(s1.charAt(0)== result.charAt(0)){
            boolean interLeaving = isInterLeaving(s1.substring(1), s2, result.substring(1));
            if(interLeaving){
                check= true;
            }
        }else if(!check && s2.charAt(0)== result.charAt(0)){
            boolean interLeaving = isInterLeaving(s1, s2.substring(1), result.substring(1));
            if(interLeaving){
                check= true;
            }
        }
        cache.put(key,check);
        return check;
    }
}
