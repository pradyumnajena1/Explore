package epp.bitmanipulation;

public class BitUtils {

  public static void main(String[] args) {
    System.out.println(Integer.toBinaryString(-16));
    System.out.println(Integer.toBinaryString(-16 >> 2));
    System.out.println(Integer.toBinaryString(-16 >>> 2));
    rightPropagateRightmostSetBit(56);
    System.out.println(moduloPowerOf2(77, 6));
    System.out.println(isPowerOf2(3));

    System.out.println(numSetBit((1 << 8) + 64 + 16 + 8 + 4 + 1));
    BitCounter counter = new BitCounter();
    System.out.println(counter.countSetBit((1 << 8) + 64 + 16 + 8 + 4 + 1));

    System.out.println(Integer.toHexString( swapBits(0xFFFFAAAA, 0, 31)));
    BitReverse bitReverse = new BitReverse();

    int x = 0xFFFFAAAA;
    System.out.println(Integer.toBinaryString(x));
    int reverseBits = bitReverse.reverseBits(x);
    System.out.println(Integer.toBinaryString(reverseBits));
  }

  public static int numSetBit(int x) {
    int count = 0;
    while (x > 0) {
      count++;
      x = x & (x - 1);
    }
    return count;
  }

  public static boolean isPowerOf2(int x) {
    return (x & (x - 1)) == 0;
  }

  public static int moduloPowerOf2(int x, int power) {
    assert power >= 0;

    int lastBit = 1 << power;
    return x & (lastBit - 1);
  }

  public static int rightPropagateRightmostSetBit(int x) {
    System.out.println(Integer.toBinaryString(x));
    int lastSetBit = getLastSetBit(x);
    System.out.println(Integer.toBinaryString(lastSetBit));
    int result = x | (lastSetBit - 1);
    System.out.println(Integer.toBinaryString(result));
    return result;
  }

  private static int getLastSetBit(int x) {
    // return 1 << Integer.numberOfTrailingZeros(x);
    return x & ~(x - 1);
  }

  public static int swapBits(int x, int i, int j) {

    if ((x >>> i & 1) != (x >>> j & 1)) {
      int mask = 1 << i | 1 << j;
      x = x ^ mask;
      return x;
    }
    return x;
  }

  public static long swapBits(long x, int i, int j) {

    if ((x >>> i & 1) != (x >>> j & 1)) {
      long mask = 1L << i | 1L << j;
      x = x ^ mask;
    }
    return x;
  }


}
