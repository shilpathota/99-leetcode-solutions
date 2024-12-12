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

I thought this is the optimal solution, but there is other solution which gives log(n) with base 2. This can be done by binary search. We know binary search is applicable for ordered elements but we twee a bit

We can view any given sequence in nums array as alternating ascending and descending sequences. By making use of this, and the fact that we can return any peak as the result, we can make use of Binary Search to find the required peak element.

In case of simple Binary Search, we work on a sorted sequence of numbers and try to find out the required number by reducing the search space at every step. In this case, we use a modification of this simple Binary Search to our advantage. We start off by finding the middle element, mid from the given nums array. If this element happens to be lying in a descending sequence of numbers. or a local falling slope(found by comparing nums[i] to its right neighbour), it means that the peak will always lie towards the left of this element. Thus, we reduce the search space to the left of mid(including itself) and perform the same process on left subarray.

If the middle element, mid lies in an ascending sequence of numbers, or a rising slope(found by comparing nums[i] to its right neighbour), it obviously implies that the peak lies towards the right of this element. Thus, we reduce the search space to the right of mid and perform the same process on the right subarray.

In this way, we keep on reducing the search space till we eventually reach a state where only one element is remaining in the search space. This single element is the peak element.

To see how it works, let's consider the three cases discussed above again.

Case 1. In this case, we firstly find 3 as the middle element. Since it lies on a falling slope, we reduce the search space to [1, 2, 3]. For this subarray, 2 happens to be the middle element, which again lies on a falling slope, reducing the search space to [1, 2]. Now, 1 acts as the middle element and it lies on a falling slope, reducing the search space to [1] only. Thus, 1 is returned as the peak correctly.

Case 2. In this case, we firstly find 3 as the middle element. Since it lies on a rising slope, we reduce the search space to [4, 5]. Now, 4 acts as the middle element for this subarray and it lies on a rising slope, reducing the search space to [5] only. Thus, 5 is returned as the peak correctly.

Case 3. In this case, the peak lies somewhere in the middle. The first middle element is 4. It lies on a rising slope, indicating that the peak lies towards its right. Thus, the search space is reduced to [5, 1]. Now, 5 happens to be the on a falling slope(relative to its right neighbour), reducing the search space to [5] only. Thus, 5 is identified as the peak element correctly.
```java
public class Solution {
    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    public int search(int[] nums, int l, int r) {
        if (l == r) return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1]) return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }
}
```
#### complexity 
Time complexity : O(log 2(n)). We reduce the search space in half at every step. Thus, the total search space will be consumed in log 2(n) steps. Here, n refers to the size of nums array.

Space complexity : O(log 2 (n)). We reduce the search space in half at every step. Thus, the total search space will be consumed in log 2 (n) steps. Thus, the depth of recursion tree will go upto log 2(n).

The binary search wiithout recursion

```java
public class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1]) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
```
This has space complexity O(1)
