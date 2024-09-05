class Solution {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        if(nums.length==0){
            return 0;
        }
        int l=0; int r=0; int maxLength=0;
        int n=nums.length;
        while(r<n){
            if(Math.abs(nums[r]-nums[l])==1){
                maxLength= Math.max(maxLength,r-l+1);
                r++;
            }else if(Math.abs(nums[r]-nums[l])<1){
                r++;
            }
            else{
                l++;
            }
        }
        return maxLength;
    }
}
