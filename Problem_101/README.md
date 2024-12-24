# 3sum

## Leet code Link - https://leetcode.com/problems/3sum/description/

## Complxity - Medium

## Description
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

#### Example 1:
```
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
```
#### Example 2:
```
Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
```
#### Example 3:
```
Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
 ```

#### Constraints:
```
3 <= nums.length <= 3000
-105 <= nums[i] <= 105
```
---
## Solution
In TwoSum we have used 2 pointer when the array is sorted and then found the sum. 

Here we have to return all the possible triplets and avoid duplicate triplets which means we have to skip any number that is repeated.

Also we have to find sum for the negative numbers in this problem because if we go over positive then it does not get the sum 0.

With all these conditions, we have two sum where we perform the comparison of low to high and sum with the given number 

1. Sort the numbers
2. check if the number is <0 and compare with the previous number and call the two sum
3. Calculate the target and if less increment low and if more decrement high.
4. We add the output

```java
class Solution {
    public void twosum(int[] nums,int i,List<List<Integer>> output){
        int low = i+1;int high = nums.length -1;
        while(low<high){
            int sum = nums[i]+nums[low]+nums[high];
            if(sum<0){
                low++;
            }
            else if(sum>0){
                high--;
            }else{
                output.add(Arrays.asList(nums[i],nums[low],nums[high]));
                low++;
                while(low<high && nums[low]==nums[low-1]) {low++;  }         
                 }
        }
    }
    public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> output = new ArrayList<>();

            Arrays.sort(nums);
            for(int i=0;i<nums.length && nums[i]<=0;i++){
                if(i==0||nums[i-1]!=nums[i]){
                    twosum(nums,i,output);
                }
            }
            return output;
    }
}
```
Sorting the array - O(N LOG N)
1 loop for first value and inner loop to calculate sum - O(N^2)

Time Complexity - O(NLOGN  + N^2) - O(N^2)

Space Complexity - O(N)
