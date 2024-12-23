# continuous Subarray sum

## Leet Code Link - https://leetcode.com/problems/continuous-subarray-sum/description/

## Complexity - Medium

## Description
Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.

A good subarray is a subarray where:

its length is at least two, and
the sum of the elements of the subarray is a multiple of k.
Note that:

A subarray is a contiguous part of the array.
An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 

#### Example 1:
```
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
```
#### Example 2:
```
Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
```
#### Example 3:
```
Input: nums = [23,2,6,4,7], k = 13
Output: false
 ```

#### Constraints:
```
1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= sum(nums[i]) <= 231 - 1
1 <= k <= 231 - 1
```
---
## Solution
As brute force approch, I can get all the subarrays and then calculate sum for each of the subarray and perform the modulo operation. This operation will take O(N^2)

What if I use prefix sums, If I store the prefix sum and then subtract the sum of the previous value as I go forward, even this might end up O(N^2)

Can I apply sliding window here? I can calculate the sum of the window as I increase the index but I do not have clarity on when to remove the element for sliding window.

Hence we use hashmap that can store the modulo of the numbers and index. If the modulo of the number occurs again which means the numbers in between sum to the multiple of k. So I can validate if the difference of index is greater than 1.

If yes we return true. Else we continue till end.

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int prefixMod =0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i=0;i<nums.length;i++){
            prefixMod = (prefixMod+nums[i])%k;
            if(map.containsKey(prefixMod)){
                if(i-map.get(prefixMod) > 1){
                    return true;
                }
            }
            else{
                map.put(prefixMod,i);
            }
        }
        return false;
    }
}
```
#### Complexity 
Let n be the number of elements in nums.

Time complexity: O(n)

We iterate through the array exactly once. In each iteration, we perform a search operation in the hashmap that takes O(1) time. Therefore, the time complexity can be stated as O(n).

Space complexity: O(n)

In each iteration, we insert a key-value pair in the hashmap. The space complexity is O(n) because the size of the hashmap is proportional to the size of the list after n iterations.
