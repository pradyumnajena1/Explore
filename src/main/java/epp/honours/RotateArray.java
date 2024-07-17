package epp.honours;

import commons.IntegerUtils;
import epp.array.ArrayUtils;

public class RotateArray {
  public static void main(String[] args) {
    int[] values = ArrayUtils.randomArray(10, 1, 20);
    ArrayUtils.printArray(values);
    rotateRight(values, 2);
    ArrayUtils.printArray(values);

    values = ArrayUtils.randomArray(10, 1, 20);
    ArrayUtils.printArray(values);
    rotateLeft(values, 2);
    ArrayUtils.printArray(values);

  }

  public static void rotateRight(int[] values, int numPlaces) {
    numPlaces = numPlaces % values.length;
    if (numPlaces == 0) {
      return;
    }

    int numCycles = IntegerUtils.gcd(values.length, numPlaces);
    int cycleLength = values.length / numCycles;
    for (int i = 0; i < numCycles; i++) {
      applyCyclicPermutationRight(values, i, cycleLength, numPlaces);
    }
  }

  public static void rotateLeft(int[] values, int numPlaces) {
    numPlaces = numPlaces % values.length;
    if (numPlaces == 0) {
      return;
    }
    //rotate left numPlaces is same as  rotate right length - numPlaces
    numPlaces = values.length - numPlaces;
    int numCycles = IntegerUtils.gcd(values.length, numPlaces);
    int cycleLength = values.length / numCycles;
    for (int i = 0; i < numCycles; i++) {
      applyCyclicPermutationRight(values, i, cycleLength, numPlaces);
    }
  }


  private static void applyCyclicPermutationRight(
      int[] values, int offset, int cycleLength, int numPlaces) {
    int temp = values[offset];
    for (int i = 1; i < cycleLength; i++) {
      int nextIndex = (offset + i * numPlaces) % values.length;
      int nextValue = values[nextIndex];
      values[nextIndex] = temp;
      temp = nextValue;
    }
    values[offset] = temp;
  }
}
