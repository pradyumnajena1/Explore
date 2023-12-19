package epp.binarySearch.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;

public class FindTop3WhereMaxItemsWeCanSortIs5AtOnce {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomUniqueArray(25,1,100);
        ArrayUtils.printArray(values);
        int[] top3 = findTop3(values);
        ArrayUtils.printArray(top3);
    }

    private static int[] findTop3(int[] values) {
        assert values.length == 25;
        int[][] sorts = new int[5][5];

        for(int i=0;i< values.length;i++){
            sorts[i/5][i%5] = values[i];
        }
        Pair[] tops = new Pair[5];
        for(int i=0;i<sorts.length;i++){
            Arrays.sort(sorts[i]);
            tops[i] = new Pair(0,i);
        }
        ArrayUtils.print2DArray(sorts);
        Arrays.sort(tops, Comparator.comparingInt(x->sorts[x.sourceIndex][x.valueIndex]));
        ArrayUtils.printArray(tops);
        int[] result = new int[3];
        result[0] = sorts[tops[0].sourceIndex][tops[0].valueIndex];
        int[] sort6 = new int[5];
        sort6[0] = sorts[tops[0].sourceIndex][++tops[0].valueIndex ];
        sort6[1] = sorts[tops[0].sourceIndex][++tops[0].valueIndex ];

        sort6[2] = sorts[tops[1].sourceIndex][ tops[1].valueIndex++ ];
        sort6[3] = sorts[tops[1].sourceIndex][ tops[1].valueIndex++ ];

        sort6[4] = sorts[tops[2].sourceIndex][ tops[2].valueIndex ];
        Arrays.sort(sort6);
        result[1] = sort6[0];
        result[2] = sort6[1];
        return result;

    }
    private static class Pair{
        int valueIndex;
        int sourceIndex;

        public Pair(int valueIndex, int sourceIndex) {
            this.valueIndex = valueIndex;
            this.sourceIndex = sourceIndex;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Pair.class.getSimpleName() + "[", "]")
                    .add("valueIndex=" + valueIndex)
                    .add("sourceIndex=" + sourceIndex)
                    .toString();
        }
    }
    private void sort(int[] values){
        if(values.length>5) throw new IllegalArgumentException("length more than 5");
        Arrays.sort(values);
    }
}
