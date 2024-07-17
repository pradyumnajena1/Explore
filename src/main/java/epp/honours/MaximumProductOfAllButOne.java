package epp.honours;

import epp.array.ArrayUtils;

public class MaximumProductOfAllButOne {
  public static void main(String[] args) {
    int[] values = {3, 2,-1,4,-1,6};// ArrayUtils.randomArray(10, -2, 10);
    ArrayUtils.printArray(values);
    int maxProduct = getMaxProductOfAllButOne(values);
    System.out.println(maxProduct);
  }

  private static int getMaxProductOfAllButOne(int[] values) {
    int[] leftProduct = new int[values.length];
    int[] rightProduct = new int[values.length];
    int product = 1;
    for (int i = 0; i < values.length; i++) {
      product *= values[i];
      if(product ==0){
        break;
      }
      leftProduct[i] = product;

    }
    product = 1;
    for (int i = values.length - 1; i >= 0; i--) {
      product *= values[i];
      if(product ==0){
        break;
      }
      rightProduct[i] = product;
    }
    int maxProduct = 0;
    for(int i=0;i<values.length;i++){
      int left = i-1>=0? leftProduct[i-1]:1;
      int right =i+1< values.length? rightProduct[i+1]:1;
      int currentProd = left*right;
      maxProduct = Math.max(maxProduct, currentProd);
    }

    return maxProduct;
  }
}
