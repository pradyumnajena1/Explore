package epp.binarySearch.revision;

import epp.ListUtils;
import epp.array.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FindTheKthElement {
  public static void main(String[] args) {
    int[] values = ArrayUtils.randomArray(10, 1, 20);
    ArrayUtils.printArray(values);
    int min5 = findKthMinItem(values, 5);
    ArrayUtils.printArray(values);
    System.out.println(min5);

    values = new int[] {99, 97, 96, 84, 96, 25, 72, 53, 93,98}; // ArrayUtils.randomArray(10, 1, 20);
    ArrayUtils.printArray(values);
    int max5 = findKthMaxItem(values, 5);

    System.out.println(max5);
    System.out.println(findMedian(values));
    Arrays.sort(values);
    ArrayUtils.printArray(values);
  }

  public static int findKthMinItem(int[] array, int k) {
    return findKthItem(array, k, 0, array.length - 1, Comparator.naturalOrder());
  }

  public static int findKthMaxItem(int[] array, int k) {
    return findKthItem(array, k, 0, array.length - 1, Comparator.reverseOrder());
  }

  public static int findMedian(int[] array) {
    if (array.length % 2 == 1) {
      int medianPosition = array.length / 2+1;
      return findKthItem(array, medianPosition, 0, array.length - 1, Comparator.naturalOrder());
    } else {
      int medianPosition = array.length / 2 ;
      int a = findKthItem(array, medianPosition, 0, array.length - 1, Comparator.naturalOrder());
      int b =
          findKthItem(array, medianPosition + 1, 0, array.length - 1, Comparator.naturalOrder());
      return (a + b) / 2;
    }
  }

  public static int findKthItem(
      int[] values, int k, int start, int end, Comparator<Integer> comparator) {
    int kthItemIndex = findKthItemIndex(values, k, start, end, comparator);
    return values[kthItemIndex];
  }

  public static int findKthItemIndex(
          int[] values, int k, int start, int end, Comparator<Integer> comparator) {
    int partition = start;
    int left = start;
    int right = end;
    while (left <= right) {
        partition = partition(values, left, right, comparator);
      if (partition - start == k - 1) {

        break;
      } else if (partition - start > k - 1) {
        right = partition - 1;
      } else {
        left = partition + 1;
      }
    }
    return partition;
  }

  public static <T> T findKthItem(T[] values, int k, Comparator<T> comparator) {
    return findKthItem(values, k, 0, values.length - 1, comparator);
  }

  public static <T> T findKthItem(List<T> values, int k, Comparator<T> comparator) {
    return findKthItem(values, k, 0, values.size() - 1, comparator);
  }

  public static <T> T findKthItem(T[] values, int k, int start, int end, Comparator<T> comparator) {
    int kthItemIndex = findKthItemIndex(values, k, start, end, comparator);
    return values[kthItemIndex];
  }

  public static <T> int findKthItemIndex(T[] values, int k, int start, int end, Comparator<T> comparator) {
    int partition = start;
    int left = start;
    int right = end;
    while (left <= right) {
        partition = partition(values, left, right, comparator);
      if (partition - start == k - 1) {

        break;
      } else if (partition - start > k - 1) {
        right = partition - 1;
      } else {
        left = partition + 1;
      }
    }
    return partition;
  }

  public static <T> T findKthItem(
      List<T> values, int k, int start, int end, Comparator<T> comparator) {
    int kthItemIndex = findKthItemIndex(values, k, start, end, comparator);
    return values.get(kthItemIndex);
  }

  public static <T> int findKthItemIndex(
          List<T> values, int k, int start, int end, Comparator<T> comparator) {
    int partition = start;
    int left = start;
    int right = end;
    while (left <= right) {
        partition = partition(values, left, right, comparator);
      if (partition - start == k - 1) {

        break;
      } else if (partition - start > k - 1) {
        right = partition - 1;
      } else {
        left = partition + 1;
      }
    }
    return partition;
  }

  public static int partition(int[] values, int start, int end, Comparator<Integer> comparator) {
    int readPosition = start;
    int writePosition = start;
    int pivot = values[end];
    while (readPosition <= end) {
      if (comparator.compare(values[readPosition], pivot) <= 0) {
        ArrayUtils.swap(values, readPosition, writePosition++);
      }
      readPosition++;
    }
    return writePosition - 1;
  }

  public static <T> int partition(T[] values, int start, int end, Comparator<T> comparator) {
    int readPosition = start;
    int writePosition = start;
    T pivot = values[end];
    while (readPosition <= end) {
      if (comparator.compare(values[readPosition], pivot) <= 0) {
        ArrayUtils.swap(values, readPosition, writePosition++);
      }
      readPosition++;
    }
    return writePosition - 1;
  }

  public static <T> int partition(List<T> values, int start, int end, Comparator<T> comparator) {
    int readPosition = start;
    int writePosition = start;
    T pivot = values.get(end);
    while (readPosition <= end) {
      if (comparator.compare(values.get(readPosition), pivot) <= 0) {
        Collections.swap(values, readPosition, writePosition++);
      }
      readPosition++;
    }
    return writePosition - 1;
  }
}
