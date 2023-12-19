package epp.array;

public class RotateSquareMatrix {

    public static void main(String[] args) {
        int[][] array = ArrayUtils.create2DMatrix(4);
        ArrayUtils.print2DArray(array);
        rotateSquareMatrix(array);
        ArrayUtils.print2DArray(array);
    }

    private static void rotateSquareMatrix(int[][] array) {
        int numLayers = (array.length+1)/2;
        for(int layer=0;layer<numLayers;layer++){
            rotateSquareMatrixLayer(array,layer);
        }
    }

    private static void rotateSquareMatrixLayer(int[][] array, int layer) {
       int[] copy =  readTopRow(array,layer);
       writeTopRow(array,layer,readLeftColumn(array,layer));
       writeLeftColumn(array,layer,readBottomRow(array,layer));
       writeBottomRow(array,layer,readRightColumn(array,layer));
       writeRightColumn(array,layer,copy);
    }

    private static int[] readTopRow(int[][] array, int layer) {
        int numElements = getNumElements(array,layer);
        int[] copy = new    int[numElements];
        int row =layer;
        int column = layer;
        for(int i=0;i<numElements;i++){
            copy[i] = array[row][column++];
        }
        return copy;
    }
    private static void writeTopRow(int[][] array, int layer,int[] elements) {
        int numElements = getNumElements(array,layer);
        int row =layer;
        int column = layer;
        for(int i=0;i<numElements;i++){
              array[row][column++] = elements[i];
        }

    }

    private static int[] readBottomRow(int[][] array, int layer) {
        int numElements = getNumElements(array,layer);
        int[] copy = new    int[numElements];
        int row =array.length-1- layer;
        int column = array.length-1- layer;
        for(int i=0;i<numElements;i++){
            copy[i] = array[row][column--];
        }
        return copy;
    }
    private static void writeBottomRow(int[][] array, int layer,int[] elements) {
        int numElements = getNumElements(array,layer);
        int row =array.length-1- layer;
        int column = array.length-1- layer;
        for(int i=0;i<numElements;i++){
            array[row][column--] = elements[i];
        }

    }

    private static int[] readLeftColumn(int[][] array, int layer) {
        int numElements = getNumElements(array,layer);
        int[] copy = new    int[numElements];
        int row =array.length-1- layer;
        int column = layer;
        for(int i=0;i<numElements;i++){
            copy[i] = array[row--][column];
        }
        return copy;
    }
    private static void writeLeftColumn(int[][] array, int layer,int[] elements) {
        int numElements = getNumElements(array,layer);
        int row =array.length-1- layer;
        int column =  layer;
        for(int i=0;i<numElements;i++){
            array[row--][column] = elements[i];
        }

    }

    private static int[] readRightColumn(int[][] array, int layer) {
        int numElements = getNumElements(array,layer);
        int[] copy = new    int[numElements];
        int row =  layer;
        int column = array.length-1- layer;
        for(int i=0;i<numElements;i++){
            copy[i] = array[row++][column];
        }
        return copy;
    }
    private static void writeRightColumn(int[][] array, int layer,int[] elements) {
        int numElements = getNumElements(array,layer);
        int row =  layer;
        int column =  array.length-1- layer;
        for(int i=0;i<numElements;i++){
            array[row++][column] = elements[i];
        }

    }


    private static int getNumElements(int[][] array, int layer) {
        return array.length-2*layer-1;
    }
}
