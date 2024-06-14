package epp.bitmanipulation;

public class BitReverse {
  private int[] preComputedReverse = new int[(int) Math.pow(2, 16)];
  private final int WORDSIZE = 16;

  private int BITMASK = 0xFFFF;

  public BitReverse() {
    for (int i = 0; i < preComputedReverse.length; i++) {
      int left = 0;
      int right = WORDSIZE - 1;
      int value = i;
      while (left < right) {
        value = BitUtils.swapBits(value, left, right);
        left++;
        right--;
      }
      preComputedReverse[i] = value;
      System.out.println(i+" "+ value);
    }
  }

  public int reverseBits(int x) {
    return preComputedReverse[x & BITMASK] << WORDSIZE
        | preComputedReverse[(x >>> WORDSIZE) & BITMASK];
  }

  public long reverseBits(long x) {
    return preComputedReverse[(int) (x & BITMASK)] << (3 * WORDSIZE)
        | preComputedReverse[(int) ((x >>> WORDSIZE) & BITMASK)] << (2 * WORDSIZE)
        | preComputedReverse[(int) ((x >>> 2 * WORDSIZE) & BITMASK)] << (WORDSIZE)
        | preComputedReverse[(int) ((x >>> 3 * WORDSIZE) & BITMASK)];
  }
}
