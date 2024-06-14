package epp.bitmanipulation;

class BitCounter {
  private int[] preComputedParity = new int[(int) Math.pow(2, 16)];

  public BitCounter() {
    for (int i = 0; i < preComputedParity.length; i++) {
      preComputedParity[i] = getNumSetBit(i);
    }
  }

  public int countSetBit(int x) {
    int wordSize = 16;
    int mask = 0xFFFF;

    int count = preComputedParity[x >>> (wordSize) & mask] + preComputedParity[x & mask];

    return count;
  }

  public int countSetBit(long x) {
    int wordSize = 16;
    int mask = 0xFFFF;

    int count =
        preComputedParity[(int) (x >>> (3 * wordSize) & mask)]
            + preComputedParity[(int) (x >>> (2 * wordSize) & mask)]
            + preComputedParity[(int) (x >>> (wordSize) & mask)]
            + preComputedParity[(int) (x & mask)];

    return count;
  }

  public int parity(int x) {
    int numSetBit = countSetBit(x);
    return (numSetBit % 2);
  }

  public int parity(long x) {
    int numSetBit = countSetBit(x);
    return (numSetBit % 2);
  }

  private int getNumSetBit(int x) {
    int count = 0;
    while (x > 0) {
      count++;
      x = x & (x - 1);
    }
    return count;
  }
}
