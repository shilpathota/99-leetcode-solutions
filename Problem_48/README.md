# Two Sum II - Input Array Is Sorted

## Leet code Link - https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/

## Complexity - Medium

## Description

Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.

Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.

The tests are generated such that there is exactly one solution. You may not use the same element twice.

Your solution must use only constant extra space.

 

#### Example 1:
```plaintext
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
```
#### Example 2:
```plaintext
Input: numbers = [2,3,4], target = 6
Output: [1,3]
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
```
#### Example 3:
```plaintext
Input: numbers = [-1,0], target = -1
Output: [1,2]
Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
 ```

#### Constraints:
```plaintext
2 <= numbers.length <= 3 * 104
-1000 <= numbers[i] <= 1000
numbers is sorted in non-decreasing order.
-1000 <= target <= 1000
The tests are generated such that there is exactly one solution.
```

## Solution

As brute force I woiuld want to loop through the elements twice with inner loop comparing the addition and target of outer loop.
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        // If no valid pair is found, return an empty array instead of null
        return new int[] {};
    }
}
```
But this solution can be optimized with using hashmap with some additional space complexity added

We can use the hashmap fo storing the value and index and compare it with target - number on iterating throuigh the numbers.

But while returning they would like the 1 added to the index.

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer,Integer> out=new HashMap<>();
        for(int i=0;i<numbers.length;i++)
        {
            int find = target-numbers[i];
            if(out.containsKey(find)){
                return new int[]{out.get(find)+1,i+1};
            }
            out.put(numbers[i],i);
        }
        return new int[0];
    }
}
```

### Complexity
**Time Complexity **-  As we are iterating the loop through 1 time N numbers it would be O(N)

**Space Complexity** - As we are using Hashmap to store all the elements, worst case stores all elements which leads to O(N)
