package epp.dp;

import java.util.*;

public class MeasureWithDefectiveMugs {

    private static class Mug{
        int low;
        int high;

        public Mug(int low, int high) {
            this.low = low;
            this.high = high;
        }
        public boolean matches(Mug other){
           return low <= other.low && high>= other.high;
        }
        public boolean contains(Mug other){
            Mug temp = this.subtract(other);
            return temp.low>0 && temp.low< temp.high;
        }
        public Mug subtract(Mug other){
            return new Mug( low-other.high,high-other.low );
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Mug{");
            sb.append("low=").append(low);
            sb.append(", high=").append(high);
            sb.append('}');
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Mug mug = (Mug) o;
            return low == mug.low && high == mug.high;
        }

        @Override
        public int hashCode() {
            return Objects.hash(low, high);
        }
    }

    public static void main(String[] args) {
        Mug[] mugs = new Mug[]{new Mug(230,240),new Mug(290,310),new Mug(500,515)};
        System.out.println(new Mug(2100,2300).contains(new Mug(230,240)));
        Map<Mug,List<Mug>> cache = new HashMap<>();
        List<Mug> steps = findSteps(mugs,2100,2300,cache);
        System.out.println(steps);
    }

    private static List<Mug> findSteps(Mug[] mugs, int low, int high,Map<Mug,List<Mug>> cache) {


        Mug reqd = new Mug(low,high);
        for(Mug mug:mugs){
            if(reqd.matches(mug)){
                ArrayList<Mug> result = new ArrayList<>();
                result.add(mug);
                return result;
            }
        }
         if(cache.containsKey(reqd)){
             return cache.get(reqd);
         }
          List<Mug> result = null;
        for(Mug mug:mugs){
            if(reqd.contains(mug) ){
                System.out.println("reqd"+ reqd +" trying "+mug);
                Mug newReqd = reqd.subtract(mug);
                List<Mug> steps = findSteps(mugs, newReqd.low, newReqd.high,cache);
                if( steps!=null){
                    steps.add(mug);
                    result= steps;
                    break;
                }
            }
        }
        cache.put(reqd,result);
        return result;
    }
}
