package epp.binaryTree;

public class BinaryIndexTree {

    public static void main(String[] args) {
        BinaryIndexTree bit = new BinaryIndexTree(new int[]{2,1,1,3,2,3,4,5,6,7,8,9});
        int sum = bit.getSum(3);
        System.out.println(sum);
    }

    private final int[] values;
    private final int[] bit;

    public BinaryIndexTree(int[] values){
        this.values = values;
        this.bit = new int[values.length+1];
        init();
    }

    public int getSum(int index){
        if(index>=values.length || index<0){
            throw new IllegalArgumentException("Index out of range");
        }
        index = index+1;
        int sum = 0;
        while (index>0){
            sum+= bit[index];
            index = index - (index & -index);
        }
        return sum;
    }
    public void update(int index,int value){
      if(index>=values.length || index<0){
          throw new IllegalArgumentException("Index out of range");
      }
      index = index+1;
      while (index<=values.length){
          bit[index]+=value;
          index = index + (index & -index);
      }
    }
    private void init(){
        for(int i=0;i<=values.length;i++){
            bit[i] = 0;
        }
        for(int i=0;i<values.length;i++){
            update(i,values[i]);
        }
    }
}
