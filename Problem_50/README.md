# Find Peak Element

## Leet Code Link - https://leetcode.com/problems/find-peak-element/description/

## Complexity - Medium

## Description
A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.

 

#### Example 1:
```plaintext
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
```
#### Example 2:
```plaintext
Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 ```

#### Constraints:
```plaintext
1 <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
nums[i] != nums[i + 1] for all valid i.
```
---

## Solution

We know that the first element is strictly greater than the previous element so I should just compare the next element. If the next element is less then I would return this index as they need any of the index.

If the next element is greater then go for next loop where I do not need to compare the previous as it moved to this number, So I just have to compare next element and return the index.

If it goes till last element, I know the last element is greater than previous element and can return its index.

```java
class Solution {
    public int findPeakElement(int[] nums) {
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]>nums[i+1]){
                return i;
            }
        }
        return nums.length-1;
    }
}
```
#### Complexity
Time Complexity - As I iterate through each element, the time complexity is O(N)

Space complexity is O(1) as I'm only using index
