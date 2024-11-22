class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums.length==0||(nums.length==1&&nums[0]==val)) return 0;
        int i=nums.length-1;
        for(int j=nums.length-1;j>=0;j--){
            if(nums[j]==val){
                nums[j]=nums[i];
                i--;
            }
        }
        return i+1;
    }
}
