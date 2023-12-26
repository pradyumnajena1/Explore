package guidetocompetitiveprogramming;

public class OptimalScheduling {
    public static void main(String[] args) {
        int[] machineTimings = new int[]{2,3,7};
        int numTasks = 8;
        int minTimeRequired = getMinTimeToFinish(machineTimings,numTasks);
        System.out.println(minTimeRequired);
    }

    /**
     * compute min time reqd to finish all jobs, given machines with time they take to finish one task
     * @param machineTimings
     * @param numTasks
     * @return
     */
    private static int getMinTimeToFinish(int[] machineTimings, int numTasks) {
        int low = 0;
        // upper bound, when we process all tasks in  a machine, choose minimum timing machine but its not absolute reqd
        int high = numTasks*machineTimings[0];
        Integer result = null;
        while (low<=high){
            int mid = (low+high)/2;
            if(isValidSchedule(mid,numTasks,machineTimings)){
                result=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }

        return result!=null?result:-1;
    }

    private static boolean isValidSchedule(int totalTime, int numTasks, int[] machineTimings) {
        int totalTasks = 0;
        for(int m:machineTimings){
            totalTasks+= totalTime/m;
        }
        return numTasks<=totalTasks;
    }
}
