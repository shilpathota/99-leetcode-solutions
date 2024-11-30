class Solution {
    int count=0;int freqelement =-1;
    public int majorityElement(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap();
        freqelement = nums[0];
        for(int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],0);
            }
            map.put(nums[i],map.get(nums[i])+1);
            if(count<map.get(nums[i])){
                freqelement = nums[i];
                count = map.get(nums[i]);
            }
        }
        return freqelement;
    }
}
