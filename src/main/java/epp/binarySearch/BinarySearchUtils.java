package epp.binarySearch;

import java.util.List;

public class BinarySearchUtils {
    public static void main(String[] args) {
        int[] array = new int[]{-14, -10, 2, 108, 108, 243, 285, 285, 285, 401};
        int index = findFirstItemBiggerThan(array, 400);
        System.out.println(index);

        index = findLastItemSmallerThan(array, 402);
        System.out.println(index);

        index = binarySearch(array, -15);
        System.out.println(index);
    }

    public static int binarySearch(int[] array, int value) {
        return binarySearch(array, 0, array.length - 1, value);
    }

    public static <T extends Comparable<T>> int binarySearch(T[] array, T value) {
        return binarySearch(array, 0, array.length - 1, value);
    }

    public static <T extends Comparable<T>> int binarySearch(List<T> values, T value) {
        return binarySearch(values, 0, values.size() - 1, value);
    }

    public static int findFirstItemBiggerThan(int[] array, int value) {
        return findFirstItemBiggerThan(array, 0, array.length - 1, value);
    }

    public static <T extends Comparable<T>> int findFirstItemBiggerThan(T[] array, T value) {
        return findFirstItemBiggerThan(array, 0, array.length - 1, value);
    }

    public static <T extends Comparable<T>> int findFirstItemBiggerThan(List<T> values, T value) {
        return findFirstItemBiggerThan(values, 0, values.size() - 1, value);
    }

    public static int findLastItemSmallerThan(int[] array, int value) {
        return findLastItemSmallerThan(array, 0, array.length - 1, value);
    }

    public static<T extends Comparable<T>> int findLastItemSmallerThan(T[] array, T value) {
        return findLastItemSmallerThan(array, 0, array.length - 1, value);
    }

    public static <T extends Comparable<T>> int findFirstItemBiggerThan(List<T> values, int start, int end, T value) {
        int low = start;
        int high = end;
        Integer index = null;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (values.get(mid).compareTo(value) < 0) {
                low = mid + 1;
            } else if (values.get(mid).compareTo(value) == 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
                index = mid;
            }
        }
        return index != null ? index : -(low + 1);
    }

    private static <T extends Comparable<T>> int findFirstItemBiggerThan(T[] array, int start, int end, T value) {
        int low = start;
        int high = end;
        Integer index = null;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid].compareTo(value) < 0) {
                low = mid + 1;
            } else if (array[mid].compareTo(value) == 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
                index = mid;
            }
        }
        return index != null ? index : -(low + 1);
    }

    public static int findFirstItemBiggerThan(int[] array, int start, int end, int value) {
        int low = start;
        int high = end;
        Integer index = null;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] < value) {
                low = mid + 1;
            } else if (array[mid] == value) {
                low = mid + 1;
            } else {
                high = mid - 1;
                index = mid;
            }
        }
        return index != null ? index : -(low + 1);
    }

    public static int findLastItemSmallerThan(int[] array, int start, int end, int value) {
        int low = start;
        int high = end;
        Integer index = null;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] < value) {
                index = mid;
                low = mid + 1;
            } else if (array[mid] == value) {
                high = mid - 1;
            } else {
                high = mid - 1;

            }
        }
        return index != null ? index : -(low + 1);
    }

    public static<T extends Comparable<T>> int findLastItemSmallerThan(T[] array, int start, int end, T value) {
        int low = start;
        int high = end;
        Integer index = null;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid].compareTo(value) < 0) {
                index = mid;
                low = mid + 1;
            } else if (array[mid].compareTo(value) == 0) {
                high = mid - 1;
            } else {
                high = mid - 1;

            }
        }
        return index != null ? index : -(low + 1);
    }

    public static<T extends Comparable<T>> int findLastItemSmallerThan(List<T> values, int start, int end, T value) {
        int low = start;
        int high = end;
        Integer index = null;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (values.get(mid).compareTo(value) < 0) {
                index = mid;
                low = mid + 1;
            } else if (values.get(mid).compareTo(value) == 0) {
                high = mid - 1;
            } else {
                high = mid - 1;

            }
        }
        return index != null ? index : -(low + 1);
    }

    public static int binarySearch(int[] array, int start, int end, int value) {
        int low = start;
        int high = end;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] < value) {
                low = mid + 1;
            } else if (array[mid] == value) {
                return mid;
            } else {
                high = mid - 1;

            }
        }
        return -(low + 1);
    }

    public static <T extends Comparable<T>> int binarySearch(T[] array, int start, int end, T value) {
        int low = start;
        int high = end;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid].compareTo(value) < 0) {
                low = mid + 1;
            } else if (array[mid].compareTo(value) == 0) {
                return mid;
            } else {
                high = mid - 1;

            }
        }
        return -(low + 1);
    }

    public static <T extends Comparable<T>> int binarySearch(List<T> values, int start, int end, T value) {
        int low = start;
        int high = end;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (values.get(mid).compareTo(value) < 0) {
                low = mid + 1;
            } else if (values.get(mid).compareTo(value) == 0) {
                return mid;
            } else {
                high = mid - 1;

            }
        }
        return -(low + 1);
    }
}
