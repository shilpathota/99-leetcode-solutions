# Next Permutation

## Leet Code link - https://leetcode.com/problems/next-permutation/description/

## Complexity - Medium

## Description
A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.

 

#### Example 1:
```
Input: nums = [1,2,3]
Output: [1,3,2]
```
#### Example 2:
```
Input: nums = [3,2,1]
Output: [1,2,3]
```
#### Example 3:
```
Input: nums = [1,1,5]
Output: [1,5,1]
 ```

#### Constraints:
```
1 <= nums.length <= 100
0 <= nums[i] <= 100
```

---
## Solution
The solution is little tricky, there should be understanding of why we are doing this to get to this solution.

We have to get the number that is next largest number that comes from permutations of these numbers. The brute force approach is finding all the permutatons and sorting it.

But given the numbers can be upto 100 it would not be feasible.

One solution that was suggested is:

<img src = "https://leetcode.com/media/original_images/31_Next_Permutation.gif" />

this gives clear on what should be done to achieve this..

- We traverse through the elements from the last. If we find the decreasing element which is greater than next element. That is the target. So take the decreased element and scan reverse from there
- Go over the elements from the highest number to the last element and compare with the element we got in previous step. If it is less than that we iterate. If we find an element greater than this element then that is to be swapped
- Now that we swapped we know that the elements after swap till end is in descending order and so reverse it.
- This will be the next largest number in this combination

```java
public class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```
#### Complexity
Let n be the size of the nums array.

Time complexity: O(n)

The first while loop runs at most n iterations, decrementing the variable i as it searches for the first decreasing element from the right. In the worst case, it checks all elements, so it takes O(n) time.

The second while loop also runs at most n iterations, decrementing the variable j as it searches for the smallest element larger than nums[i]. Similarly, it can take O(n) time.

The reverse function is called on a portion of the array, from index i + 1 to the end. In the worst case, this can cover the entire array, leading to a time complexity of O(n).

The swap function runs in constant time, O(1), since it only exchanges two elements.

Therefore, the overall time complexity is O(n).

Space complexity: O(1)
