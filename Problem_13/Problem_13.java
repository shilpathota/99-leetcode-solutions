class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int index =-1;
        if(target<nums[left]) return 0;
        else if(target>nums[right]) return right+1;
        while(left<=right){
            int middle = (int) Math.floor((left+right)/2);
            if(nums[middle]==target){
                index = middle;
                break;
            }
            else if(nums[middle]<target){
                left = middle+1;
            }
            else{
                right = middle-1;
            }
            index=left;
        }
        return index;
    }
}
