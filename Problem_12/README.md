# Maximum Average Subarray I

## Leet code link - https://leetcode.com/problems/maximum-average-subarray-i/description/

## Complexity - Easy

## Description

You are given an integer array nums consisting of n elements, and an integer k.<br/>

Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.<br/>

 

Example 1:<br/>

Input: nums = [1,12,-5,-6,50,3], k = 4<br/>
Output: 12.75000<br/>
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75<br/>
Example 2:<br/>

Input: nums = [5], k = 1<br/>
Output: 5.00000<br/>
 

Constraints:<br/>

n == nums.length<br/>
1 <= k <= n <= 105<br/>
-104 <= nums[i] <= 104<br/>

## Solution

We can use simple sliding window technique for this problem
