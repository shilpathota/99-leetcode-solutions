# Remove Duplicates from Sorted Array II

## Leet Code Link - https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/

## Complexity - Medium

## Description

Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with `O(1)` extra memory.

#### Custom Judge:

The judge will test your solution with the following code:
```plaintext
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
```
If all assertions pass, then your solution will be accepted.

 

#### Example 1:
```plaintext
Input: nums = [1,1,1,2,2,3]
Output: 5, nums = [1,1,2,2,3,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
```
#### Example 2:
```plaintext
Input: nums = [0,0,1,1,1,1,2,3,3]
Output: 7, nums = [0,0,1,1,2,3,3,_,_]
Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
 ```

#### Constraints:
```plaintext
1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.
```
---
## Solution
The second approach is really inspired by the fact that the problem statement asks us to return the new length of the array from the function. If all we had to do was remove elements, the function would not really ask us to return the updated length. However, in our scenario, this is really an indication that we don't need to actually remove elements from the array. Instead, we can do something better and simply overwrite the duplicate elements that are unwanted.

We won't be able to achieve this using a single pointer. We will be using a two-pointer approach where one pointer iterates over the original set of elements and another one that keeps track of the next "empty" location in the array or the next location that can be overwritten in the array.

#### Algorithm

We define two pointers, i and j for our algorithm. The pointer i iterates of the array processing one element at a time and j keeps track of the next location in the array where we can overwrite an element.

We also keep a variable count which keeps track of the count of a particular element in the array. Note that the minimum count would always be 1.

We start with index 1 and process one element at a time in the array.

If we find that the current element is the same as the previous element i.e. nums[i] == nums[i - 1], then we increment the count. If the value of count > 2, then we have encountered an unwanted duplicate element. In this case, we simply move forward i.e. we increment i but not j.

However, if the count is <= 2, then we can move the element from index i to index j.
![image](https://github.com/user-attachments/assets/653c325c-c506-4ac3-a9f3-da2b17493e5a)


If we encounter that the current element is not the same as the previous element i.e. nums[i] != nums[i - 1], then it means we have a new element at hand and so accordingly, we update count = 1 and also move this element to index j.
![image](https://github.com/user-attachments/assets/4d744994-a728-4807-9725-f47773332609)


![image](https://github.com/user-attachments/assets/60116cbd-f5d0-49b1-9b14-9fabaeeaeb55)

```java
class Solution {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int i = 1; // Pointer to iterate through the array
        int j = 1; // Pointer to track position for valid elements
        int count = 1; // Count of occurrences of the current element

        while (i < nums.length) {
            if (nums[i] == nums[i - 1]) {
                count++;
                if (count > 2) {
                    i++;
                    continue;
                }
            } else {
                count = 1;
            }
            nums[j] = nums[i];
            j++;
            i++;
        }

        // Java arrays can't be resized like C++ vectors,
        // so we return the size directly.
        return j;
    }
}
```
Simplifying this even more we know that we dont track only the previous element but previous to previous element

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        int k = 2;
        for(int i = 2; i < nums.length ; i++){
            if(nums[i] != nums[k-2]){
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}
```
