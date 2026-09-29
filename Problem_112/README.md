# 229. Majority Element II

## Description
Given an integer array of size n, find all elements that appear more than ⌊n / 3⌋ times.

 

Example 1:

Input: nums = [3,2,3]
Output: [3]
Example 2:

Input: nums = [1]
Output: [1]
Example 3:

Input: nums = [1,2]
Output: [1,2]
 

Constraints:

1 <= nums.length <= 5 * 104
-109 <= nums[i] <= 109
 

Follow up: Could you solve the problem in linear time and in O(1) space?

## Solution
We can use the Boyer-Moore Solution to get O(1) space but here I implemented using hash map and storing the frequency of the elements which has O(k) space complexity where k is number of unique elements.

```
class Solution:
    def majorityElement(self, nums: list[int]) -> list[int]:
        freq = {}
        for num in nums:
            freq[num] = freq.get(num, 0) + 1
        return [num for num,count in freq.items() if count>len(nums)//3]


```
