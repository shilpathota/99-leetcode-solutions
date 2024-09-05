# Longest Harmonious Subsequence
## Leet code Link - https://leetcode.com/problems/longest-harmonious-subsequence/description/
## Complexity - Easy
## Description
We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.<br/>

Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.<br/>

A subsequence of array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.<br/>

 

Example 1:<br/>

Input: nums = [1,3,2,2,5,2,3,7]<br/>
Output: 5<br/>
Explanation: The longest harmonious subsequence is [3,2,2,2,3].<br/>
Example 2:<br/>

Input: nums = [1,2,3,4]<br/>
Output: 2<br/>
Example 3:<br/>

Input: nums = [1,1,1,1]<br/>
Output: 0<br/>
 

Constraints:<br/>

1 <= nums.length <= 2 * 104<br/>
-109 <= nums[i] <= 109<br/>

## Solution
We are using sliding window algorithm
