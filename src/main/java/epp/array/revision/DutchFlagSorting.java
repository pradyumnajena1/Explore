package epp.array.revision;

import epp.array.ArrayUtils;
import java.util.StringJoiner;
import java.util.function.Supplier;

public class DutchFlagSorting {
    public static void main(String[] args) {
        int[] randomArray = ArrayUtils.randomArray(15,2,10);
        ArrayUtils.printArray(randomArray);
        int pivotIndex = 7;
        System.out.println("pivot " + randomArray[pivotIndex]);
        dutchFlagSort(randomArray, pivotIndex);
        ArrayUtils.printArray(randomArray);
       // ArrayUtils.printArray(randomArray);

        Person[] people = new Person[]{new Person(true,1),
                new Person(false,2),
                new Person(true,3),
                new Person(false,4)};
        sort(people);
        ArrayUtils.printArray(people);

        int[] values = {1, 4, 2, 3, 3, 2, 1, 4};
        dutchFlagSortWithFourValues(values);
        ArrayUtils.printArray(values);
    }

    private static void dutchFlagSort(int[] values, int index) {
        int pivot = values[index];
        int sp=0,ep = 0;
        int bp = values.length-1;
        while (ep<=bp){
            if(values[ep]<pivot){
                ArrayUtils.swap(values,sp,ep);
                sp++;
                ep++;

            }else if(values[ep]==pivot){
                ep++;
            }else{
                ArrayUtils.swap(values,ep,bp);
                bp--;
            }
        }
    }

  /**
   * Given an array A of n objects with keys that takes one of four values, reorder
   * thearrayso that allobjects that have thesame keyappear together. Use0(1)additional
   * space and 0(n) time.
   */
  private static void dutchFlagSortWithFourValues(int[] values) {

      int ap=0,bp = 0,cp=0;
      int dp = values.length-1;
      while (cp<=dp){
          if(values[cp]==1){
              ArrayUtils.swap(values,ap,cp);
              ap++;
              bp++;
              cp++;

          }else if(values[cp]==2){
              ArrayUtils.swap(values,bp,cp);
              bp++;
              cp++;
          }else if(values[cp]==3){
              cp++;
          }

          else{
              ArrayUtils.swap(values,cp,dp);
              dp--;
          }
      }
  }

  /**
   * Given an array A of n objects with Boolean-valued keys, reorder the array so
   * that objects that have the key false appear first. The relative ordering of objects with
   * key true should not change. Use 0(1) additional space and 0(n) time.
   */

     private static void sort(Supplier<Boolean>[] values){
         int writeIndex = values.length-1;
         for(int i=values.length-1;i>=0;i--){
             if(values[i].get()==Boolean.TRUE){
                 ArrayUtils.swap(values,i,writeIndex--);
             }
         }
     }

    private static class Person implements Supplier<Boolean> {
        private boolean key;
        private int id;

        public Person(boolean key,int id) {
            this.key = key;
            this.id = id;
        }

        @Override
        public Boolean get() {
            return key;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                    .add("key=" + key)
                    .add("id=" + id)
                    .toString();
        }
    }


}
