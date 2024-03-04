package cph;

/**
 * set of 32 items
 */
public class BitSet {
    private int data;
    public BitSet() {
        this(0);
    }
    public BitSet(int data){
        this.data=data;
    }
    public void add(int value){
        value =value & 31;
        data = data | (1<<value);
    }
    public void remove(int value){
        value =value & 31;
        data = data & ~(1<<value);
    }
    public void print(){
        for(int i=0;i<32;i++){
            if((data & 1<<i)>0){
                System.out.print(i+" ");
            }
        }
        System.out.println();
    }
    public void interSection(BitSet bitSet){
         this.data =this.data & bitSet.data;
    }
    public void union(BitSet bitSet){
       this.data =  this.data | bitSet.data;
    }
    public void minus(BitSet bitSet){
        this.data =  this.data &  ~bitSet.data;
    }

    public static void main(String[] args) {
        BitSet set = new BitSet();
        set.add(1);
        set.add(3);
        set.add(7);
        set.add(8);
        set.print();
        set.remove(7);
        set.print();
        BitSet b = new BitSet();
        b.add(2);
        b.add(4);
        set.union(b);
        set.print();
        set.interSection(b);
        set.print();
        set.add(3);
        set.print();
        set.minus(b);
        set.print();
    }
}
