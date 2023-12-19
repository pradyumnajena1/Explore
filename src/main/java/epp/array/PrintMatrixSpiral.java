package epp.array;

public class PrintMatrixSpiral {
    public static void main(String[] args) {
        int[][] array = ArrayUtils.create2DMatrix(4);
        ArrayUtils.print2DArray(array);
        printMatrixSpiral(array);
    }

    private static void printMatrixSpiral(int[][] array) {
        int numLayers = (array.length+1)/2;
        for(int layer=0;layer<numLayers;layer++){
            printMatrixSpiralLayer(array,layer);
        }
    }

    private static void printMatrixSpiralLayer(int[][] array, int layer) {

        printTopRow(array,layer);
        printRightColumn(array,layer);
        printBottomRow(array,layer);
        printLeftColumn(array,layer);
    }

    private static void printLeftColumn(int[][] array, int layer) {
        int numElements = getNumElements(array, layer);
        int row = array.length-layer-1;
        int col = layer;
        for(int i = 0;i<numElements;i++){
            System.out.print(array[row--][col]+" ,");
        }
    }

    private static void printBottomRow(int[][] array, int layer) {
        int numElements = getNumElements(array, layer);
        int row = array.length- layer-1;
        int col = array.length- layer-1;
        for(int i = 0;i<numElements;i++){
            System.out.print(array[row][col--]+" ,");
        }
    }

    private static void printRightColumn(int[][] array, int layer) {
        int numElements = getNumElements(array, layer);
        int row = layer;
        int col = array.length- layer-1;
        for(int i = 0;i<numElements;i++){
            System.out.print(array[row++][col]+" ,");
        }
    }

    private static void printTopRow(int[][] array, int layer) {
        int numElements = getNumElements(array, layer);
        int row = layer;
        int col = layer;
        for(int i = 0;i<numElements;i++){
            System.out.print(array[row][col++]+" ,");
        }
    }

    private static int getNumElements(int[][] array, int layer) {
        return array.length - 2 * layer - 1;
    }
}
