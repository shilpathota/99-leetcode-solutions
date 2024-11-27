# Two Sum

## Leet Code Link - https://leetcode.com/problems/two-sum/description/

## Complexity - Easy

## Description
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

#### Example 1:
```plaintext
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
```

#### Example 2:
```plaintext
Input: nums = [3,2,4], target = 6
Output: [1,2]
```

##### Example 3:
```plaintext
Input: nums = [3,3], target = 6
Output: [0,1]
```
#### Constraints
```plaintext
2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.
```
---
### Solution
The two pointer cannot be applied here as the values in the array are not in ascending order or descending order 
```java
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
```
