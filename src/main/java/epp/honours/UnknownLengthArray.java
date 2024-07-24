package epp.honours;

public class UnknownLengthArray {

  public static void main(String[] args) {
    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8,9, 10};
    int item = 12;
    int index = search(numbers, item);
    System.out.println("index of  " + item + " = " + index);
  }

  private static int search(int[] values, int value) {
    int p = 0;
    while (true) {

      try {
        int index = (1 << p) - 1;
        if (values[index] == value) {
          return index;
        } else if (values[index] > value) {
          break;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        break;
      }
      p++;
    }
    int left = Math.max(0, 1 << (p - 1));
    int right = (1 << p) - 1;
    while (left <= right) {

      int mid = left + (right - left) / 2;
      try {
        if (values[mid] == value) {
          return mid;
        } else if (values[mid] > value) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        right = mid - 1;
      }
    }
    return -(left + 1);
  }
}
