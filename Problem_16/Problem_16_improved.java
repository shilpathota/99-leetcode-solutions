class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length<=1) return nums.length;
        int i=1;

        for(int j=1;j<nums.length;j++){
            if(nums[j-1]!=nums[j]){
                        nums[i]=nums[j];
                        i++;
            }
            
        }
        return i;

    }
}
