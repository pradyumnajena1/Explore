package meta;

import java.util.Arrays;

public class ThreesumClosest {
    public static void main(String[] args) {
        ThreesumClosest threesumClosest = new ThreesumClosest();
        System.out.println(threesumClosest.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(threesumClosest.threeSumClosest(new int[]{0,0,0}, 1));
    }
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        Integer value = null;

        for(int i=0;i< nums.length;i++){
            for(int j=i+1;j< nums.length;j++){
                int reqdSum = target- (nums[i]+nums[j]);
                int index = Arrays.binarySearch(nums, 0, i, reqdSum);
                if(index>=0){
                    return (nums[i]+nums[j]+ nums[index]);
                }else{
                    index = -(index+1);

                    if(index>0 &&   closest  >   reqdSum -   nums[index-1]){
                        closest =   reqdSum -   nums[index-1];
                        value = (nums[i]+nums[j]+ nums[index-1]);
                    }
                    if(index<i && closest> nums[index]  -reqdSum){
                        closest =   nums[index]  -reqdSum;
                        value = (nums[i]+nums[j]+ nums[index]);
                    }
                }
            }
        }
          return value;

    }
}
