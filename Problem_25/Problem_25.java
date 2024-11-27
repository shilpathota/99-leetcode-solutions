class Solution {
    public int[] twoSum(int[] nums, int target) {
        int j = nums.length-1;
        HashMap<Integer,Integer> hashmap=new HashMap<>();
        for(int n=0;n<=j;n++){
            int find = target-nums[n];
            if(hashmap.containsKey(find)){
                return new int[]{hashmap.get(find), n};
            }
            
                 hashmap.put(nums[n],n); 
        }
        return new int[0];
    }
}
