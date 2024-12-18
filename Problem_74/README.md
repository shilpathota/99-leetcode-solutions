# Kth missing positive number

## Leet Code Link - https://leetcode.com/problems/kth-missing-positive-number/description/

## Complexity - Easy

## Description
Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Return the kth positive integer that is missing from this array.

 

#### Example 1:
```
Input: arr = [2,3,4,7,11], k = 5
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
```
#### Example 2:
```
Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 ```

#### Constraints:
```
1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length
 ```

Follow up:

Could you solve this problem in less than O(n) complexity?

---
## Solution

WE have linear search where we start the count from 1 and as we progress through the loop we compare the count with the element. If element is equal to count and it is less than the length of the array then we increment count

Else we increment missing count

If the missing count equals k then return the value. 

```java
class Solution {
    public int findKthPositive(int[] arr, int k) {
        int missingCount = 0; // Tracks how many numbers are missing
        int current = 1; // Start from the first positive integer
        int index = 0; // Pointer for the array

        while (true) {
            if (index < arr.length && arr[index] == current) {
                // If the current number is in the array, move to the next
                index++;
            } else {
                // If current is missing, increase the missing count
                missingCount++;
                if (missingCount == k) {
                    // Return the current number as soon as we find the kth missing
                    return current;
                }
            }
            current++; // Move to the next number
        }
    }
}
```
#### Complexity 
Time complexity: O(N) because in the worst case, we have to parse all array elements.

Space complexity: O(1), we don't allocate any additional data structures.

AS this is sorted array, we can definitely think of binary search for optimized solution

```java
class Solution {
    public int findKthPositive(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int pivot = left + (right - left) / 2;
            // If number of positive integers
            // which are missing before arr[pivot]
            // is less than k -->
            // continue to search on the right.
            if (arr[pivot] - pivot - 1 < k) {
                left = pivot + 1;
            // Otherwise, go left.
            } else {
                right = pivot - 1;
            }
        }

        // At the end of the loop, left = right + 1,
        // and the kth missing is in-between arr[right] and arr[left].
        // The number of integers missing before arr[right] is
        // arr[right] - right - 1 -->
        // the number to return is
        // arr[right] + k - (arr[right] - right - 1) = k + left
        return left + k;
    }
}
```
Why Binary Search Works: The missing count is an increasing function, so we can use binary search to efficiently find the range where the kth missing number lies.

Final Calculation (k + high + 1): After the loop, high represents the last index where the number of missing integers was less than k. The kth missing number is k units ahead of the adjusted range (high + 1).

Time: 
𝑂
(
log
⁡
𝑛
)
O(logn), as we perform binary search on the array.
Space: 
𝑂
(
1
)
O(1) (constant space).

