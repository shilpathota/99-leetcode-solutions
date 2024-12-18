# Squares of Sorted Array

## Leet Code Link - https://leetcode.com/problems/squares-of-a-sorted-array/description/

## Complexity - Easy

## Description
Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

 

#### Example 1:
```
Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
```
#### Example 2:
```
Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]
 ```

#### Constraints:
```
1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.
 ```

Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?

---
## Solution
On looking the question we feel it is stright forward. So Let us use Java 8 streams

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        return Arrays.stream(nums).map(i->i*i).sorted().toArray();
    }
}
```
This is O(NlogN) time Complexity and O(N) space complexity

But the followup question says we have to use O(N)

Which means you should not use sorted method and should iterate the items only once

We can use two pointer approach for this. One pointing to 0 and other pointing to last element. First element is negative. So Squaring it will be greater so we can compare with right squared.

If it is less than right squared, move the right pointer and if it is greater than right add the element and move the left pointer.

```java
class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int i=0;int j=nums.length-1;int k=nums.length-1;
        while(j>=i && k>=0){
            int numsi=nums[i]*nums[i];
            int numsj=nums[j]*nums[j];
            if(numsj>numsi){
                result[k]=numsj;
                j--;
                k--;
            }else if(numsj<numsi){
                result[k]=numsi;
                k--;
                i++;
            }
            else{
                result[k]=numsi;
                k--;
                if(i!=j){
                result[k]=numsj;
                k--;
                }

                j--;
                i++;
            }
        }
                return result;
    }
}
```
Time Complexity: O(N), where N is the length of A.

Space Complexity: O(N) if you take output into account and O(1) otherwise.
