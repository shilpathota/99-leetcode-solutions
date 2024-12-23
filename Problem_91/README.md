# Binary Search

## Leet code Link - https://leetcode.com/problems/binary-search/description/

## Complexity - easy

## Description
Given an array of integers nums which is sorted in ascending order, and an integer target, write a function to search target in nums. If target exists, then return its index. Otherwise, return -1.

You must write an algorithm with O(log n) runtime complexity.

 

#### Example 1:
```
Input: nums = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
```
#### Example 2:
```
Input: nums = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1
 ```

#### Constraints:
```
1 <= nums.length <= 104
-104 < nums[i], target < 104
All the integers in nums are unique.
nums is sorted in ascending order.
```
## Solution

As the binary search is the direct solution where we use recursive approcah to recursively go over the elements to find the target

```java
class Solution {
    public int binarysearch(int[] nums,int target,int low,int high){
        if(low<=high){
        int mid = low + (high-low)/2;
        if(nums[mid]==target){
            return mid;
        }
        else if(nums[mid]>target){
           return binarysearch(nums,target,low,mid-1);
        }
        else{
           return binarysearch(nums,target,mid+1,high);
        }
        }
        else return -1;
    }
    public int search(int[] nums, int target) {
        int low=0,high=nums.length-1;
        return binarysearch(nums, target,low,high);
    }
}
```

But there is an efficient way as recursive approcah uses stack and so memory complexity increases

```java
class Solution {
    public int search(int[] nums, int target) {
        // Set the left and right boundaries
        int left = 0, right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        if (left < nums.length && nums[left] == target) {
            return left;
        } else {
            return -1;
        } 
    }
}
```
#### Complexity 
Let n be the size of the input array nums.

Time complexity: O(logn)

nums is divided into half each time. In the worst-case scenario, we need to cut nums until the range has no element, it takes logarithmic time to reach this break condition.

Space complexity: O(1)

During the loop, we only need to record three indexes, left, right, and mid, they take constant space.
