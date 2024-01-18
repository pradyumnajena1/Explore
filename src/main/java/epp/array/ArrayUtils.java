package epp.array;

import epp.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ArrayUtils {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomSortedArray(20, 1, 10);
        Integer[] integers = Arrays.stream(values).boxed().toArray(Integer[]::new);
        ArrayUtils.printArray(integers);
        System.out.println(lastIndexOf(integers, integers[2]));
        System.out.println(firstIndexOf(integers, integers[2]));
    }

  public   static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException(
                    "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0 ||  fromIndex >= arrayLength) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (  toIndex >= arrayLength) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int[] randomArray(int size, int minValue, int maxValue) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = minValue + (int) (Math.random() * (maxValue - minValue));
        }
        return array;
    }

    public static double[] randomArray(int size, double minValue, double maxValue) {
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = minValue + (double) (Math.random() * (maxValue - minValue));
        }
        return array;
    }

    public static int[] randomSortedArray(int size, int minValue, int maxValue) {
        int[] array = randomArray(size, minValue, maxValue);
        Arrays.sort(array);
        return array;
    }
    public static int[] randomSortedUniqueArray(int size, int minValue, int maxValue) {
        int[] array = randomUniqueArray(size, minValue, maxValue);
        Arrays.sort(array);
        return array;
    }
    public  static    void rotateArrayRight(Object[] values, int numPlaces) {
        int current = 0;
        Object currentValue = values[current];
        do {

            int next = (current + numPlaces) % values.length;
            Object nextValue = values[next];

            values[next] = currentValue;

            current = next;
            currentValue = nextValue;

        } while (current != 0);
    }


    public static int[] randomUniqueArray(int size, int minValue, int maxValue) {
        int[] array = new int[maxValue - minValue + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = minValue + i;
        }
        shuffle(array);
        int[] result = new int[size];
        System.arraycopy(array, 0, result, 0, size);
        return result;
    }

    public static void reverse(int[] array) {
        int left = 0;
        int right = array.length - 1;
        reverse(array, left, right);
    }
    public static void reverse(char[] array) {
        int left = 0;
        int right = array.length - 1;
        reverse(array, left, right);
    }

    private static void reverse(char[] array, int left, int right) {
        while (left < right) {
            swap(array, left++, right--);
        }
    }

    public static void reverse(int[] array, int left, int right) {
        while (left < right) {
            swap(array, left++, right--);
        }
    }

    public static void rotateArray(int[] array, int numPosition) {
        numPosition = numPosition % array.length;
        if (numPosition == 0) {
            return;
        }
        reverse(array, 0, array.length - numPosition - 1);
        reverse(array, array.length - numPosition, array.length - 1);
        reverse(array, 0, array.length - 1);
    }

    public static int[][] create2DMatrix(int size) {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < size * size; i++) {
            values.add(i + 1);
        }
        return createMNMatrix(size, size, values);
    }

    public static int[][] createRandomMNMatrix(int rows, int cols, int minValue, int maxValue) {
        int[] ints = ArrayUtils.randomArray(rows * cols, minValue, maxValue);
        List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
        return createMNMatrix(rows, cols, list);
    }

    public static int[][] createRandomIncreasingMNMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        int[] topRow = randomSortedUniqueArray(cols,1,cols+5);
        for(int col = 0;col<cols;col++){
            matrix[0][col] = topRow[col];
        }
        int[] firstCol = randomSortedUniqueArray(cols,topRow[0],rows+5);
        firstCol[0] = topRow[0];
        for(int row=0;row<rows;row++){
            matrix[row][0] = firstCol[row];
        }
        for(int row=1;row<rows;row++){
            for(int col=1;col<cols;col++){
                matrix[row][col] = Math.max(matrix[row-1][col],matrix[row][col-1]) + 1+ (int)(Math.random()*5);
            }
        }
         return matrix;
    }

    public static int[][] createMNMatrix(int rows, int cols, List<Integer> values) {
        int readIndex = 0;
        int[][] array = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = values.get(readIndex++);
            }
        }
        return array;
    }
    public static <T> T[] createArray(Class<T> type,int size){
        T[] instance = (T[]) Array.newInstance(type, size);
        return instance;
    }

    public static void replaceValue(int[] array, int oldValue, int newValue) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == oldValue) {
                array[i] = newValue;
            }
        }
    }

    public static void replaceValue(int[][] array, int oldValue, int newValue) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == oldValue) {
                    array[i][j] = newValue;
                }
            }

        }
    }
    public static void replaceValue(int[][] array, Set<Integer> oldValues, int newValue) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (oldValues.contains(array[i][j])) {
                    array[i][j] = newValue;
                }
            }

        }
    }

    public static void print2DArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }
        System.out.println();

    }

    public static void print2DArray(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }

    }

    public static void print2DArray(Object[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(Arrays.toString(array[i]));
        }

    }


    public static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int readPosition = start;
        int writePosition = start;

        while (readPosition <= end) {
            if (array[readPosition] <= pivot) {
                swap(array, readPosition, writePosition);
                writePosition++;
                readPosition++;
            } else {
                readPosition++;
            }
        }
        return writePosition - 1;
    }

    public static <T extends Comparable<T>> int partition(T[] array, int start, int end) {
        T pivot = array[end];
        int readPosition = start;
        int writePosition = start;

        while (readPosition <= end) {
            if (array[readPosition].compareTo(pivot) <= 0) {
                swap(array, readPosition, writePosition);
                writePosition++;
                readPosition++;
            } else {
                readPosition++;
            }
        }
        return writePosition - 1;
    }

    public static Integer findNthItem(int[] array, int start, int end, int n) {

        while (n > 0 && n <= end - start + 1) {
            int position = partition(array, start, end);
            if (position - start == n - 1) {
                return array[position];
            } else if (position - start > n - 1) {
                return findNthItem(array, start, position - 1, n);
            } else {
                return findNthItem(array, position + 1, end, n - position + start - 1);
            }

        }
        return null;
    }

    public static <T extends Comparable<T>> T findNthItem(T[] array, int start, int end, int n) {

        Integer nthItemIndex = findNthItemIndex(array, start, end, n);
        if (nthItemIndex != null) {
            return array[nthItemIndex];
        }
        return null;
    }

    public static <T extends Comparable<T>> Integer findNthItemIndex(T[] array, int start, int end, int n) {

        while (n > 0 && n <= end - start + 1) {
            int position = partition(array, start, end);
            if (position - start == n - 1) {
                return position;
            } else if (position - start > n - 1) {
                return findNthItemIndex(array, start, position - 1, n);
            } else {
                return findNthItemIndex(array, position + 1, end, n - position + start - 1);
            }

        }
        return null;
    }

    public static int findNthItem(int[] array, int n) {
        return findNthItem(array, 0, array.length - 1, n);
    }

    public static <T extends Comparable<T>> T findNthItem(T[] array, int n) {
        return findNthItem(array, 0, array.length - 1, n);
    }

    public static int binarySearch(int[] array, int value) {

        return binarySearch(array, value, 0, array.length - 1);
    }

    public static int binarySearch(int[] array, int value, int start, int end) {
        int low = start;
        int high = end;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int[][] get2DSortedArray(int size) {
        int[][] matrix = new int[size][size];
        int[] randomFirstRow = randomArray(size, 1, 10);
        Arrays.sort(randomFirstRow);
        matrix[0] = randomFirstRow;
        int[] randomFirstColumn = randomArray(size - 1, matrix[0][0], 15);
        Arrays.sort(randomFirstColumn);
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] = randomFirstColumn[i - 1];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]) + (int) (Math.random() * size);
            }
        }
        return matrix;
    }

    public static void printArray(int[] array) {

        System.out.println(Arrays.toString(array));
    }
    public static void printArray(long[] array) {

        System.out.println(Arrays.toString(array));
    }
    public static void printArray(String message, int[] array) {

        System.out.println(message+" "+ Arrays.toString(array));
    }
    public static void printArray(char[] array) {
        System.out.println(Arrays.toString(array));
    }
    public static void printArray(Object[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void printArray(double[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void printArray(int[] array, int start, int end) {
        if (start > end || start < 0 || end > array.length - 1) {
            throw new IllegalArgumentException(" invalid endpoints: start" + start + " end" + end + " length" + array.length);
        }
        int length = end - start + 1;
        int[] dest = new int[length];
        System.arraycopy(array, start, dest, 0, length);
        System.out.println(Arrays.toString(dest));
    }

    public static int[] cumulativeSum(int[] values) {
        int[] cumsum = new int[values.length];
        cumsum[0] = values[0];
        for (int i = 1; i < values.length; i++) {
            cumsum[i] = cumsum[i - 1] + values[i];
        }
        return cumsum;
    }
    public static  void cumulativeSumInPlace(int[] values) {
        for (int i = 1; i < values.length; i++) {
            values[i] = values[i - 1] + values[i];
        }

    }

    public static int[] cumulativeSumRight(int[] values) {
        int[] cumsum = new int[values.length];
        cumsum[cumsum.length - 1] = values[values.length - 1];
        for (int i = cumsum.length - 2; i >= 0; i--) {
            cumsum[i] = cumsum[i + 1] + values[i];
        }
        return cumsum;
    }

    public static void shuffle(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int selectedIndex = (int) (Math.random() * (array.length - i));
            swap(array, selectedIndex, array.length - 1 - i);
        }
    }

    public static<T extends Comparable<T>> int lastIndexOf(T[] values,T value){
        return lastIndexOf(values,value,0,values.length-1);
    }
    public static  int lastIndexOf(int[] values,int value){
        return lastIndexOf(values,value,0,values.length-1);
    }

    public static<T extends Comparable<T>> int firstIndexOf(T[] values,T value){
        return firstIndexOf(values,value,0,values.length-1);
    }

    private static <T extends Comparable<T>> int lastIndexOf(T[] values, T value, int start, int end) {
        int low = start;
        int high = end;
        Integer lastIndex = null;
        while (low<=high){
            int mid = (low+high)/2;
            int compare = value.compareTo(values[mid]);
            if(compare ==0){
                lastIndex=mid;
                low=mid+1;
            }else if(compare<0){
                high = mid-1;
            }else{
                low=mid+1;
            }
        }
        return lastIndex!=null?lastIndex:-1;
    }

    public static   int lastIndexOf(int[] values, int value, int start, int end) {
        int low = start;
        int high = end;
        Integer lastIndex = null;
        while (low<=high){
            int mid = (low+high)/2;
            int compare = Integer.compare(value, values[mid]);
            if(compare ==0){
                lastIndex=mid;
                low=mid+1;
            }else if(compare<0){
                high = mid-1;
            }else{
                low=mid+1;
            }
        }
        return lastIndex!=null?lastIndex:-1;
    }

    private static <T extends Comparable<T>> int firstIndexOf(T[] values, T value, int start, int end) {
        int low = start;
        int high = end;
        Integer lastIndex = null;
        while (low<=high){
            int mid = (low+high)/2;
            int compare = value.compareTo(values[mid]);
            if(compare ==0){
                lastIndex=mid;
                high=mid-1;
            }else if(compare<0){
                high = mid-1;
            }else{
                low=mid+1;
            }
        }
        return lastIndex!=null?lastIndex:-1;
    }

    private static  int firstIndexOf(int[] values, int value, int start, int end) {
        int low = start;
        int high = end;
        Integer lastIndex = null;
        while (low<=high){
            int mid = (low+high)/2;
            int compare = Integer.compare(value,values[mid]);
            if(compare ==0){
                lastIndex=mid;
                high=mid-1;
            }else if(compare<0){
                high = mid-1;
            }else{
                low=mid+1;
            }
        }
        return lastIndex!=null?lastIndex:-1;
    }

    public static int greaterOrEqual(int[] nums,int start,int end,int value){
          int low = start;
          int high = end;
          int index = -1;
          while (low<=high){
              int mid = (low+high)/2;
              if(nums[mid]==value){
                  index=mid;
                  break;
              }else if(nums[mid]<value){
                  low=mid+1;
              }else{
                  index = mid;
                  high = mid-1;
              }
          }
          return index;
     }

    public static int findFirstElementGreaterThan(int[] values, int value) {
        return findFirstElementGreaterThan(values,value,0,values.length-1);
    }

    public static int findLastElementSmallerThan(int[] values, int value) {
        return findLastElementSmallerThan(values,value,0,values.length-1);
    }

    public static int findLastElementSmallerThan(int[] values, int value, int start, int end) {
        Integer index  = null;
        while (start<=end){
            int mid = start+(end-start)/2;
            if(values[mid]<value){
                index = mid;
                start=mid+1;
            }else{
                end=mid-1;
            }
        }
        return index!=null?index:-(start+1);
    }

    public static int findFirstElementGreaterThan(int[] values, int value, int start, int end) {
        Integer index  = null;
        while (start<=end){
            int mid = start+(end-start)/2;
            if(value<values[mid]){
                index = mid;
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return index!=null?index:-(start+1);
    }

    public static List<Pair<Integer, Integer>> getNeighbours(int maxRow,int maxCol, Pair<Integer, Integer> source) {

        return getNeighbours(maxRow,maxCol, source , false);
    }
    public static List<Pair<Integer, Integer>> getNeighbours(int maxRow,int maxCol, Pair<Integer, Integer> source,boolean includeCorners) {

        return getNeighbours(maxRow,maxCol, source.getFirst(), source.getSecond(), includeCorners);
    }

    public static List<Pair<Integer, Integer>> getNeighbours(int maxRow,int maxCol, int row, int col,
                                                             boolean includeCorners) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (row + i >= 0 && row + i <= maxRow && col + j >= 0 && col + j <= maxCol) {
                    if (!(i == 0 && j == 0) && (includeCorners || Math.abs(i * j) != 1)) {

                        result.add(new Pair<>(row + i, col + j));
                    }
                }
            }
        }
        return result;
    }
    public static List<Pair<Integer,Integer>> getRowCells(int[][] matrix,List<Integer> rowNums){
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        for(int row:rowNums){
            list.addAll(getRowCells(matrix,row));
        }
        return list;

    }
    public static List<Pair<Integer,Integer>> getColCells(int[][] matrix,List<Integer> colNums){
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        for(int col:colNums){
            list.addAll(getColCells(matrix,col));
        }
        return list;

    }
    public static List<Pair<Integer,Integer>> getRowCells(int[][] matrix,int rowNum){
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        for(int i=0;i<matrix[0].length;i++){
            list.add(new Pair<>(rowNum,i));
        }
        return list;
    }
    public static List<Pair<Integer,Integer>> getColCells(int[][] matrix,int col){
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        for(int i=0;i<matrix.length;i++){
            list.add(new Pair<>(i,col));
        }
        return list;
    }

    public static void printArray(boolean[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static int[][] generatePascalTriangle(int numRows) {

        return generatePascalTriangle(numRows,false);
    }

    public static int[][] generatePascalTriangle(int numRows,boolean random) {
        int[][] pascalTriangle = new int[numRows][];
        for (int i = 0; i < numRows; i++) {
            int length = i + 1;
            pascalTriangle[i] = new int[length];
            pascalTriangle[i][0] =random? (int) (Math.random()*10):1;
            pascalTriangle[i][length-1] =random?  (int) (Math.random()*10):1;
        }

        for (int row = 1; row < numRows; row++) {
            int[] aboveRow = pascalTriangle[row - 1];
            for (int col = 1; col < pascalTriangle[row].length - 1; col++) {
                int left =random? (int)(Math.random()*10): aboveRow[col - 1];
                int right =random? (int)(Math.random()*10): aboveRow[col];
                pascalTriangle[row][col] = left + right;
            }
        }

        return pascalTriangle;
    }
}
