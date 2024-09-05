class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
       HashMap<Integer,Integer> val= new HashMap<>();
       for(int i=0;i<nums.length;i++){
        if(val.containsKey(nums[i])){
            if(Math.abs(val.get(nums[i])-i)<=k){
                return true;
            }else{
                val.put(nums[i],i);
            }
        }else{
            val.put(nums[i],i);
        }
       }
       return false;
    }
}
