# Find First and Last Position of Element in Sorted Array

## Leet code Link - https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/

## Complexity - Medium

## Description
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

 

#### Example 1:
```
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
```
#### Example 2:
```
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
```
#### Example 3:
```
Input: nums = [], target = 0
Output: [-1,-1]
 ```

#### Constraints:
```
0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
```

---
## Solution
As the values of array is sorted. I thought 2 pointer technique will work as I progress through the elenents I can check if equal to target and if yes, we can wait till other pointer also reaches target and return it. Else return the -1 -1. But the edge case here is if there is only 1 element in the array and it is equal to target.

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int i=0;int j=nums.length-1;
        if(nums.length ==1&&nums[0]==target) return new int[]{i,j};
        while(i<j){
            if(nums[i]!=target){
                i++;
            }
            if(nums[j]!=target){
                j--;
            }
            if(nums[i]==target && nums[j]==target){
                return new int[]{i,j};
            }
        }
        return new int[]{-1,-1};
    }
}
```
#### Complexity 
Time Complexity is O(N)

Space Complexity is O(1)

We can also use binary search for this problem.

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int firstOccurrence = this.findBound(nums, target, true);

        if (firstOccurrence == -1) {
            return new int[] { -1, -1 };
        }

        int lastOccurrence = this.findBound(nums, target, false);

        return new int[] { firstOccurrence, lastOccurrence };
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int N = nums.length;
        int begin = 0, end = N - 1;

        while (begin <= end) {
            int mid = (begin + end) / 2;

            if (nums[mid] == target) {
                if (isFirst) {
                    // This means we found our lower bound.
                    if (mid == begin || nums[mid - 1] != target) {
                        return mid;
                    }

                    // Search on the left side for the bound.
                    end = mid - 1;
                } else {
                    // This means we found our upper bound.
                    if (mid == end || nums[mid + 1] != target) {
                        return mid;
                    }

                    // Search on the right side for the bound.
                    begin = mid + 1;
                }
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }

        return -1;
    }
}
```
#### Complexity

Time Complexity: O(logN) considering there are N elements in the array. This is because binary search takes logarithmic time to scan an array of N elements. Why? Because at each step we discard half of the array we are scanning and hence, we're done after a logarithmic number of steps. We simply perform binary search twice in this case.

Space Complexity: O(1) since we only use space for a few variables and our result array, all of which require constant space.
