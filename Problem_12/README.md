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

```java
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int l=0;int r=0;
        int n=nums.length;
        double MaxAvg=0.0;
        int currentAvg=0;
        for(int i=0;i<k;i++){
            currentAvg+=nums[i];
        }
        MaxAvg=(double)currentAvg/k;
        for(int i=0;i+k<=n-1;i++){
            currentAvg-=nums[i];
            currentAvg+=nums[i+k];
            MaxAvg=Math.max(MaxAvg,(double)currentAvg/k);
        }        
        return MaxAvg;
    }
}
```

## Complexity 
Time complexity : O(n). We iterate over the given nums array of length n once only.

Space complexity : O(1). Constant extra space is used.
