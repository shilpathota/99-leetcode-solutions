class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int l=0;int r=0;
        int n=nums.length;
        double MaxAvg=0.0;
        int currentAvg=0;
        for(int i=0;i<k;i++){
            currentAvg+=nums[i];
        }
        MaxAvg=(double)currentAvg/k;
        for(int i=0;i+k<=n-1;i++){
            currentAvg-=nums[i];
            currentAvg+=nums[i+k];
            MaxAvg=Math.max(MaxAvg,(double)currentAvg/k);
        }        
        return MaxAvg;
    }
}
