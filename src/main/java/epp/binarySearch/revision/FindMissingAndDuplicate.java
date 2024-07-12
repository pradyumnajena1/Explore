package epp.binarySearch.revision;

import java.util.StringJoiner;

public class FindMissingAndDuplicate {

    public static void main(String[] args){
        int[] values = {0,1,2,3,4,5,6,11,8,9,10,11,12};
        MissingDuplicate missingDuplicate = findMissingAndDuplicate(values);
    System.out.println(missingDuplicate);
    }

    private static MissingDuplicate findMissingAndDuplicate(int[] values) {
        int missingXORDuplicate = 0;
        for(int i=0;i<values.length;i++){
            missingXORDuplicate^=i;
            missingXORDuplicate^=values[i];
        }
        int lastSetBit = Integer.lowestOneBit(missingXORDuplicate);
       int  missOrDup = 0;
        for(int i=0;i<values.length;i++){
            if((values[i] & lastSetBit) != 0){
                missOrDup^=values[i];
            }
            if( (i & lastSetBit) != 0){
                missOrDup^=i;
            }
        }
        for( int i=0; i<values.length;i++ ){
            if(values[i] == missOrDup){
                return new MissingDuplicate(missingXORDuplicate^ missOrDup,missOrDup);
            }
        }

        return new MissingDuplicate(missOrDup,missingXORDuplicate^ missOrDup);
    }

    private static class MissingDuplicate{
        int missing;
        int duplicate;

        public MissingDuplicate(int missing, int duplicate) {
            this.missing = missing;
            this.duplicate = duplicate;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", MissingDuplicate.class.getSimpleName() + "[", "]")
                    .add("missing=" + missing)
                    .add("duplicate=" + duplicate)
                    .toString();
        }
    }
}
