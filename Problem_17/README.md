# Remove Duplicates from Sorted Array

## LeetCode Link - https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/

## Complexity - Easy

### Description
Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
Return k.
#### Custom Judge:

The judge will test your solution with the following code:
<pre>
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.
</pre>
 

#### Example 1:

**Input:** nums = [1,1,2]

**Output:** 2, nums = [1,2,_]

**Explanation:** Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.

It does not matter what you leave beyond the returned k (hence they are underscores).

#### Example 2:

**Input:** nums = [0,0,1,1,1,2,2,3,3,4]

**Output:** 5, nums = [0,1,2,3,4,_,_,_,_,_]

**Explanation:** Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.

It does not matter what you leave beyond the returned k (hence they are underscores).
 

#### Constraints:

1 <= nums.length <= 3 * 104

-100 <= nums[i] <= 100

nums is sorted in non-decreasing order.

### Solution

The algorithmic technique used is Two pointer Technique which gives better Time complexity compared to naive method. This is because we are considering 2 values for each iteration and so the complexity of using 2 loops is reduced and this works only for sorted arrays.

#### Pseudo Code
<pre>
    1. Consider two pointers. 1 for loop and moves till the end of the array while the other moves only if there is unique element found by 1st pointer
    2. Consider the first element is unique so start from second element and compare the element with previous element if the value is not same which means it is unique.
    3. copy the value of unique value into the location of second pointer and increment the second pointer.
    4. Once the loop is complete the second pointer points to the array index till which there are unique elements and so return it
</pre>

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length<=1) return nums.length;
        int i=1;

        for(int j=1;j<nums.length;j++){
            if(nums[j-1]!=nums[j]){
                        nums[i]=nums[j];
                        i++;
            }
            
        }
        return i;

    }
}
```
