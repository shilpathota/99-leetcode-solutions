# Move Zeroes

## Leet code Link - https://leetcode.com/problems/move-zeroes/description/

## Complexity - Easy

## Description 
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

 

## Example 1:
```plaintext
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
```
## Example 2:
```plaintext
Input: nums = [0]
Output: [0]
 ```

## Constraints:
```plaintext
1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1
 ```

Follow up: Could you minimize the total number of operations done?

## Solution
We can coy the non zero elements into a different array and then put them back or use other data structure to do that but it is said we should do operations without copying.

So we should use swaping in place to achieve this.

We would want to iterate through the array get the number of zeros and keep the pointer at that place in the array. So when I found a zero I can swap with that index. This increases the time complexity

So instead of keeping the pointer at the start of number of zeros, lets have the pointer L and R start at 0 and R moves towards the end checking for the elements if non zero and L be the pointer keeping track of non zero elements already visited 

```java
class Solution {
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void moveZeroes(int[] nums) {
        int L=0; int R=0; 
        while(R<nums.length){
            if(nums[R]!=0){
                swap(nums,L,R);
                L++;
            }
            R++;
        }
    }
}
```
## complexity
Time Complexity -- We are iterating through n elements so the complexity would be O(N).

Space Complexity -- We are using temp variable and other L and R pointer which is constant complexity and O(1)

